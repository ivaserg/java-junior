package com.acme.edu.exception;

/**
 * Created by vanbkin on 03.09.2017.
 */
public abstract class LoggerBaseException extends RuntimeException {
    public LoggerBaseException(String message) {
        super(message);
    }
}
