package com.acme.edu.handler;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;
import com.acme.edu.event.ByteMessageLoggedEvent;
import com.acme.edu.framework.Handler;

public class ByteMessageLoggedEventHandler implements Handler<ByteMessageLoggedEvent>{
    private static final String TYPE_DESCRIPTION = "primitive: ";
    private int aggregatedValue;

    private Saver saver;
    private Formatter formatter;

    public ByteMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }

    @Override
    public void onEvent(ByteMessageLoggedEvent event) {
        if (event.isCollectionNeeded()) {
            int  currentValue = Byte.valueOf(event.getMessage());
            if (Byte.MAX_VALUE - aggregatedValue < currentValue) {  // overFlow
                saver.save(formatter.format(TYPE_DESCRIPTION + aggregatedValue));
                aggregatedValue = currentValue;
            } else if (aggregatedValue + currentValue < Byte.MIN_VALUE) {
                saver.save(formatter.format(TYPE_DESCRIPTION + aggregatedValue));
                aggregatedValue = currentValue;
            } else {
                aggregatedValue += currentValue;
            }
        } else {
            aggregatedValue += Byte.valueOf(event.getMessage());
            saver.save(formatter.format(TYPE_DESCRIPTION + aggregatedValue));
            aggregatedValue=0;
        }



    }

}
