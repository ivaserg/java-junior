package com.acme.edu.handler;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;
import com.acme.edu.event.IntMessageLoggedEvent;
import com.acme.edu.framework.Handler;

public class IntMessageLoggedEventHandler implements Handler<IntMessageLoggedEvent> {
    private static final String TYPE_DESCRIPTION = "primitive: ";
    private int aggregatedValue;

    private Saver saver;
    private Formatter formatter;

    public IntMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }

    @Override
    public void onEvent(IntMessageLoggedEvent event) {
        if (event.isCollectionNeeded()) {
            int currentValue = Integer.valueOf(event.getMessage());
            if (Integer.MAX_VALUE - aggregatedValue < currentValue) {  // overFlow
                saver.save(formatter.format(TYPE_DESCRIPTION + aggregatedValue));
                aggregatedValue = currentValue;
            } else if (Integer.MIN_VALUE + aggregatedValue > currentValue) {
                saver.save(formatter.format(TYPE_DESCRIPTION + aggregatedValue));
                aggregatedValue = currentValue;
            } else {
                aggregatedValue += currentValue;
            }

         } else {
            aggregatedValue += Integer.valueOf(event.getMessage());
            saver.save(formatter.format(TYPE_DESCRIPTION + aggregatedValue));
            aggregatedValue=0;
        }

    }
}
