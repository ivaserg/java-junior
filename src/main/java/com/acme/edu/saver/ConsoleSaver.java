package com.acme.edu.saver;

import com.acme.edu.exception.MessageSavingException;

import java.io.IOException;

public class ConsoleSaver implements Saver {
    @Override
    public void save(String message) throws MessageSavingException {

        try {
            System.out.println(message);
        } catch (Exception e) {
            throw new MessageSavingException("Failed to log message to console", e.getCause());
        }
    }
}
