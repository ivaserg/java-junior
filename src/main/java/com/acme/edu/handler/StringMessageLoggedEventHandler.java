package com.acme.edu.handler;

import com.acme.edu.formatter.DefaultFormatter;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.ConsoleSaver;
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
        String currentMessage = event.getMessage();
        if (event.isCollectionNeeded()) {
            if (currentMessage.equals(cachedMessage) || cachedMessage.isEmpty()) {
                cachedMessage = currentMessage;
                aggregatedValue++;
            } else {
                saver.save(formatter.format(TYPE_DESCRIPTION + cachedMessage + getNumberOfCachedMessages()));
                cachedMessage = currentMessage;
                aggregatedValue=1;
            }
        } else {
            if (currentMessage.equals(cachedMessage) || cachedMessage.isEmpty())  {
                aggregatedValue++;
                saver.save(formatter.format(TYPE_DESCRIPTION + currentMessage + getNumberOfCachedMessages()));
                aggregatedValue=0;
                cachedMessage="";
            } else {
                saver.save(formatter.format(TYPE_DESCRIPTION + cachedMessage + getNumberOfCachedMessages()));
                aggregatedValue=0;
                saver.save(formatter.format(TYPE_DESCRIPTION + currentMessage + getNumberOfCachedMessages()));
                cachedMessage="";
            }

        }

    }

    private String getNumberOfCachedMessages() {
        return aggregatedValue > 1 ? " (x" + aggregatedValue + ")" : "";
    }

    public static void main(String[] args) {
        StringMessageLoggedEventHandler sut = new StringMessageLoggedEventHandler(new ConsoleSaver(), new DefaultFormatter());

        sut.onEvent(new StringMessageLoggedEvent("str 1", true));
        sut.onEvent(new StringMessageLoggedEvent("str 1", true));
        sut.onEvent(new StringMessageLoggedEvent("str 3", true));
        sut.onEvent(new StringMessageLoggedEvent("str 4", true));
        sut.onEvent(new StringMessageLoggedEvent("str 4", true));
        sut.onEvent(new StringMessageLoggedEvent("str 4", true));
        sut.onEvent(new StringMessageLoggedEvent("str 4", true));
        sut.onEvent(new StringMessageLoggedEvent("str 4", true));
        sut.onEvent(new StringMessageLoggedEvent("str 1", false));
    }
}
