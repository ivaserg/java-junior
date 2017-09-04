package com.acme.edu.handler;

import com.acme.edu.event.StringMessageLoggedEvent;
import com.acme.edu.exception.MessageFormattingException;
import com.acme.edu.exception.MessageHandlingException;
import com.acme.edu.exception.MessageSavingException;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.Handler;
import com.acme.edu.saver.Saver;

public class StringMessageLoggedEventHandler implements Handler<StringMessageLoggedEvent> {
    private static final String TYPE_DESCRIPTION = "string: ";
    private String cachedMessage = "";
    private int aggregatedValue;

    private Saver saver;
    private Formatter formatter;

    public StringMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }

    @Override
    public void onEvent(StringMessageLoggedEvent event) throws MessageHandlingException {
        String currentMessage = event.getMessage();
        if (event.isCollectionNeeded()) {
            if (currentMessage.equals(cachedMessage) || cachedMessage.isEmpty()) {
                cachedMessage = currentMessage;
                aggregatedValue++;
            } else {
                processMessage(TYPE_DESCRIPTION + cachedMessage + getNumberOfCachedMessages());
                cachedMessage = currentMessage;
                aggregatedValue = 1;
            }
        } else {
            if (currentMessage.equals(cachedMessage) || cachedMessage.isEmpty()) {
                aggregatedValue++;
                processMessage(TYPE_DESCRIPTION + currentMessage + getNumberOfCachedMessages());
                aggregatedValue = 0;
                cachedMessage = "";
            } else {
                processMessage(TYPE_DESCRIPTION + cachedMessage + getNumberOfCachedMessages());
                aggregatedValue = 0;
                processMessage(TYPE_DESCRIPTION + currentMessage + getNumberOfCachedMessages());
                cachedMessage = "";
            }

        }

    }

    private void processMessage(String message) throws MessageHandlingException {
        try {
            saver.save(formatter.format(message));
        } catch (MessageSavingException e) {
            throw new MessageHandlingException(String.format("Failed to save STRING message: \"%s\"", message), e.getCause());
        } catch (MessageFormattingException e) {
            throw new MessageHandlingException(String.format("Failed to format STRING message: \"%s\"", message), e.getCause());
        }
    }

    private String getNumberOfCachedMessages() {
        return aggregatedValue > 1 ? " (x" + aggregatedValue + ")" : "";
    }


}
