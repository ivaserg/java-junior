package com.acme.edu.exception;

public class MessageHandlingException extends RuntimeException {
    public MessageHandlingException(String message, Throwable cause) {
        super(message, cause);
    }
}
