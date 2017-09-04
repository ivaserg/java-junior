package com.acme.edu.exception;

public class MessageFormattingException extends MessageHandlingException {
    public MessageFormattingException(String message, Throwable cause) {
        super(message, cause);
    }
}
