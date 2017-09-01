package com.acme.edu.event;

public class ObjectMessageLoggedEvent extends AbstractEvent {
    private String message;

    public ObjectMessageLoggedEvent(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
