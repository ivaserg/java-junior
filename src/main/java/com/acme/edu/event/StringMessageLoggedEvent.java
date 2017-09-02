package com.acme.edu.event;

public class StringMessageLoggedEvent extends AbstractEvent {
    private String message;
    private boolean collectionNeeded;

    public StringMessageLoggedEvent(String message) {
        this.message = message;
    }

    public StringMessageLoggedEvent(String message, boolean collectionNeeded) {
        this.message = message;
        this.collectionNeeded = collectionNeeded;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCollectionNeeded() {
        return collectionNeeded;
    }
}
