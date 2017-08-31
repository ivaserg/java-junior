package com.acme.edu.Formatters;

import com.acme.edu.Messages.LogMessage;

public interface Formatter {

    void format(LogMessage logMessage);

    String format(String message);
}
