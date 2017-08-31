package com.acme.edu.event;

import com.acme.edu.LoggerState;
import com.acme.edu.State;

public class FlushCacheEvent extends AbstractEvent {
    private State previousState;

    public FlushCacheEvent(State previousState) {
        this.previousState = previousState;
    }

    public State getPreviousState() {
        return previousState;
    }
}
