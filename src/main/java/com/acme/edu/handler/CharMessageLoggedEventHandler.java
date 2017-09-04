package com.acme.edu.handler;

import com.acme.edu.exception.MessageFormattingException;
import com.acme.edu.exception.MessageHandlingException;
import com.acme.edu.exception.MessageSavingException;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import com.acme.edu.event.CharMessageLoggedEvent;
import com.acme.edu.framework.Handler;

public class CharMessageLoggedEventHandler implements Handler<CharMessageLoggedEvent> {
    private static final String TYPE_DESCRIPTION = "char: ";

    private Saver saver;
    private Formatter formatter;

    public CharMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }

    @Override
    public void onEvent(CharMessageLoggedEvent event) throws MessageHandlingException {
        processMessage(TYPE_DESCRIPTION+event.getMessage());
    }

    private void processMessage(String message) throws MessageHandlingException {
        try {
            saver.save(formatter.format(message));
        } catch (MessageSavingException e) {
            throw new MessageHandlingException(String.format("Failed to save CHAR message: \"%s\"", message), e.getCause());
        } catch (MessageFormattingException e) {
            throw new MessageHandlingException(String.format("Failed to format CHAR message: \"%s\"", message), e.getCause());
        }
    }
}
