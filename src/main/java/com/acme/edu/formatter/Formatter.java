package com.acme.edu.formatter;

import com.acme.edu.exception.MessageFormattingException;

public interface Formatter {

    String format(String message) throws MessageFormattingException;
}
