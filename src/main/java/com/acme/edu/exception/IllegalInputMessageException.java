package com.acme.edu.exception;

public class IllegalInputMessageException extends RuntimeException {
    public IllegalInputMessageException(String message) {
        super(message);
    }
}
