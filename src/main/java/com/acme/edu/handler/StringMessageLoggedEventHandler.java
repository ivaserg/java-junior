package com.acme.edu.handler;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Messages.StringMessage;
import com.acme.edu.Savers.Saver;
import com.acme.edu.event.IntMessageLoggedEvent;
import com.acme.edu.event.StringMessageLoggedEvent;
import com.acme.edu.framework.Handler;

public class StringMessageLoggedEventHandler implements Handler<StringMessageLoggedEvent> {
    private static final String TYPE_DESCRIPTION = "string: ";
    private String cachedMessage;
    private int aggregatedValue;

    private Saver saver;
    private Formatter formatter;

    public StringMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }

    @Override
    public void onEvent(StringMessageLoggedEvent event) {
        if (event.isCollectionNeeded() || event.getMessage().equals(cachedMessage)) {
            aggregatedValue++;
            cachedMessage = event.getMessage();
        } else {
            String numberOfStrings = aggregatedValue > 1 ? " (x" + aggregatedValue + ")" : "";
            saver.save(formatter.format(TYPE_DESCRIPTION + event.getMessage() + numberOfStrings));
            aggregatedValue=0;
            cachedMessage="";
        }

    }
}
