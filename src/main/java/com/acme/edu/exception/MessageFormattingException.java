package com.acme.edu.exception;

public class MessageFormattingException extends Exception {
    public MessageFormattingException(String message, Throwable cause) {
        super(message, cause);
    }
}
