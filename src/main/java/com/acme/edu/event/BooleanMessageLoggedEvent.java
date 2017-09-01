package com.acme.edu.event;

public class BooleanMessageLoggedEvent extends AbstractEvent {
    private String message;

    public String getMessage() {
        return message;
    }

    public BooleanMessageLoggedEvent(String message) {
        this.message = message;
    }
}
