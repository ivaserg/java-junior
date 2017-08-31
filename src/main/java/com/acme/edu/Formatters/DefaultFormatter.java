package com.acme.edu.Formatters;

import com.acme.edu.Messages.LogMessage;

/**
 * Created by vanbkin on 26.08.2017.
 */
public class DefaultFormatter implements Formatter {

    @Override
    public void format(LogMessage logMessage) {
        // NULL-Object
    }

    @Override
    public String format(String message) {
        return message;
    }
}
