package com.acme.edu.event;

import com.acme.edu.MessageEventHandlerState;
import com.acme.edu.State;

public class FlushCacheEvent extends AbstractEvent {
    private MessageEventHandlerState messageEventHandlerState;

    public FlushCacheEvent(MessageEventHandlerState messageEventHandlerState) {
        this.messageEventHandlerState = messageEventHandlerState;
    }

    public State getPreviousState() {
        return messageEventHandlerState.getPreviousState();
    }
}
