package com.acme.edu.exception;

/**
 * Created by vanbkin on 04.09.2017.
 */
public class NoHandlerException extends RuntimeException {
    public NoHandlerException(String message) {
        super(message);
    }
}
