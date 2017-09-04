package com.acme.edu.exception;

public class LoggerBaseAPIException extends RuntimeException {
    public LoggerBaseAPIException(String message) {
        super(message);
    }
}
