package com.acme.edu.event;

public class IntMessageLoggedEvent extends AbstractEvent {
    private String message;
    private boolean collectionNeeded;

    public IntMessageLoggedEvent(String message, boolean collectionNeeded) {
        this.message = message;
        this.collectionNeeded = collectionNeeded;
    }


    public boolean isCollectionNeeded() {
        return collectionNeeded;
    }

    public String getMessage() {
        return message;
    }

}
