package com.acme.edu.saver;

import com.acme.edu.exception.MessageHandlingException;
import com.acme.edu.exception.MessageSavingException;

import java.io.IOException;

public class ConsoleSaver implements Saver {
    @Override
    public void save(String message) throws MessageSavingException {
        try {
            System.out.println(message);
            throw new IOException("failed to find file");
        } catch (Exception e) {
            MessageSavingException exception = new MessageSavingException("Failed to save message to console", e.getCause());
            exception.addSuppressed(e);
            throw exception;
        }
    }
}
