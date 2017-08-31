package com.acme.edu.event;

import com.acme.edu.framework.Event;
import com.acme.edu.framework.EventDispatcher;

public abstract class AbstractEvent implements Event {

    /**
     * Returns the event type as a {@link Class} object
     * In this example, this method is used by the {@link EventDispatcher} to
     * dispatch events depending on their type.
     *
     * @return the AbstractEvent type as a {@link Class}.
     */
    public Class<? extends Event> getType() {
        return getClass();
    }
}
