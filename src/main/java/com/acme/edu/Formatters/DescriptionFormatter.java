package com.acme.edu.Formatters;

import com.acme.edu.Messages.LogMessage;

public class DescriptionFormatter implements Formatter {

    @Override
    public void format(LogMessage logMessage) {
        logMessage.setMessage(logMessage.getTypeDescription() + logMessage.getMessage());
    }

    @Override
    public String format(String message) {
        return message;
    }
}
