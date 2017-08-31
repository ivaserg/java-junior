package com.acme.edu.event;

public class ByteMessageLoggedEvent extends AbstractEvent {
    private String message;
    private boolean collectionNeeded;

    public boolean isCollectionNeeded() {
        return collectionNeeded;
    }

    public String getMessage() {
        return message;
    }

    public ByteMessageLoggedEvent(String message, boolean collectionNeeded) {
        this.message = message;
        this.collectionNeeded = collectionNeeded;
    }
}
