package com.acme.edu.exception;

public class MessageSavingException extends Exception {
    public MessageSavingException(String message, Throwable cause) {
        super(message, cause);
    }
}
