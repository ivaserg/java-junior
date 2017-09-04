package com.acme.edu.exception;

public class MessageHandlingException extends LoggerBaseException {
    public MessageHandlingException(String message, Throwable cause) {
        super(message, cause);
    }
}
