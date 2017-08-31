package com.acme.edu.event;

public class CharMessageLoggedEvent extends AbstractEvent {
    private String message;

    public String getMessage() {
        return message;
    }

    public CharMessageLoggedEvent(String message) {

        this.message = message;
    }
}
