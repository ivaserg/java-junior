package com.acme.edu.handler;

import com.acme.edu.event.IntMessageLoggedEvent;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.Handler;
import com.acme.edu.saver.Saver;

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
        int currentValue = Integer.parseInt(event.getMessage());
        if (event.isCollectionNeeded()) {
            if (isOverflow(currentValue)) {  // overFlow
                saver.save(formatter.format(TYPE_DESCRIPTION + aggregatedValue));
                aggregatedValue = currentValue;
            }
             else {
                aggregatedValue += currentValue;
            }

         } else {
            if (isOverflow(currentValue)) {  // overFlow
                saver.save(formatter.format(TYPE_DESCRIPTION + aggregatedValue));
                saver.save(formatter.format(TYPE_DESCRIPTION + currentValue));
            }
            else {
                aggregatedValue += currentValue;
                saver.save(formatter.format(TYPE_DESCRIPTION + aggregatedValue));
            }

            aggregatedValue=0;
        }

    }

    public boolean isOverflow(int currentValue) {
        if (aggregatedValue > 0 && currentValue > 0) {
          return Integer.MAX_VALUE - aggregatedValue < currentValue;
        }
        else if (aggregatedValue < 0 && currentValue < 0) {
            return  Integer.MIN_VALUE  - aggregatedValue >  currentValue;
        }
        else {
            return false;
        }

    }

}
