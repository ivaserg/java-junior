package com.acme.edu.saver;

import com.acme.edu.exception.MessageSavingException;

import java.io.IOException;

public class LogSaver implements Saver {
    @Override
    public void save(String message) throws MessageSavingException {
        try {


        } catch (Exception e) {
            MessageSavingException exception = new MessageSavingException("Failed to save message to file", e.getCause());
            exception.addSuppressed(e);
            throw exception;
        }
    }
}
