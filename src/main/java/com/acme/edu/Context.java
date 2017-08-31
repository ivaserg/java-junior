package com.acme.edu;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Context {
    List<MessageEventHandler> messageEventHandlers = new ArrayList<>();

    public void addHandler(MessageEventHandler messageEventHandler) {
        messageEventHandlers.add(messageEventHandler);
    }

    public Context(MessageEventHandler... handlers) {
            this.messageEventHandlers.addAll(asList(handlers));
    }

    public void notifyHandlers(Object message) {
        messageEventHandlers.forEach((h) -> h.handleEvent(message));
    }


}
