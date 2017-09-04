package com.acme.edu.formatter;

import com.acme.edu.exception.MessageFormattingException;

/**
 * Created by vanbkin on 26.08.2017.
 */
public class DefaultFormatter implements Formatter {

    @Override
    public String format(String message) throws MessageFormattingException {
        try {

        } catch (Exception e) {
            throw new MessageFormattingException("Failed to correctly format message", e.getCause());
        }

        return message;
    }
}
