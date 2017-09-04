package com.acme.edu.exception;

public class MessageSavingException extends MessageHandlingException {
    public MessageSavingException(String message, Throwable cause) {
        super(message, cause);
    }
}
