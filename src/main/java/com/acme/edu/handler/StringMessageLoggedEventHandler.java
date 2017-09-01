package com.acme.edu.handler;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import com.acme.edu.event.StringMessageLoggedEvent;
import com.acme.edu.framework.Handler;

public class StringMessageLoggedEventHandler implements Handler<StringMessageLoggedEvent> {
    private static final String TYPE_DESCRIPTION = "string: ";
    private String cachedMessage="";
    private int aggregatedValue;

    private Saver saver;
    private Formatter formatter;

    public StringMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }

    @Override
    public void onEvent(StringMessageLoggedEvent event) {
        if (event.isCollectionNeeded()) {
            if (event.getMessage().equals(cachedMessage) || cachedMessage.isEmpty()) {
                cachedMessage = event.getMessage();
                aggregatedValue++;
            } else {
                String numberOfStrings = aggregatedValue > 1 ? " (x" + aggregatedValue + ")" : "";
                saver.save(formatter.format(TYPE_DESCRIPTION + cachedMessage + numberOfStrings));
                cachedMessage = event.getMessage();
                aggregatedValue=1;
            }
        } else {
            String numberOfStrings = aggregatedValue > 1 ? " (x" + aggregatedValue + ")" : "";
            saver.save(formatter.format(TYPE_DESCRIPTION + cachedMessage + numberOfStrings));
            aggregatedValue=0;
            cachedMessage="";
        }

    }
}
