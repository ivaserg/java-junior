package com.acme.edu.handler;

import com.acme.edu.exception.LoggerBaseException;
import com.acme.edu.exception.MessageHandlingException;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.Event;
import com.acme.edu.saver.Saver;
import com.acme.edu.State;
import com.acme.edu.event.ByteMessageLoggedEvent;
import com.acme.edu.event.IntMessageLoggedEvent;
import com.acme.edu.event.FlushCacheEvent;
import com.acme.edu.event.StringMessageLoggedEvent;
import com.acme.edu.framework.EventDispatcher;
import com.acme.edu.framework.Handler;

public class FlushCacheEventHandler implements Handler<FlushCacheEvent> {
    private Saver saver;
    private Formatter formatter;
    private EventDispatcher dispatcher;

    public FlushCacheEventHandler(Saver saver, Formatter formatter, EventDispatcher dispatcher) {
        this.saver = saver;
        this.formatter = formatter;
        this.dispatcher=dispatcher;
    }

    @Override
    public void onEvent(FlushCacheEvent event) throws LoggerBaseException {
        State state = event.getPreviousState();
        switch (state) {
            case INT_INPUT:
                dispatchEvent(new IntMessageLoggedEvent("0", false));
                break;
            case BYTE_INPUT:
                dispatchEvent(new ByteMessageLoggedEvent("0", false));
                break;
            case STRING_INPUT:
                dispatchEvent(new StringMessageLoggedEvent("", false));
                break;


        }

    }

    private void dispatchEvent(Event event) throws LoggerBaseException {
        try {
            dispatcher.dispatch(event);
        } catch (MessageHandlingException e) {
            throw new LoggerBaseException("Failed to dispatch message", e.getCause());
        }
    }
}
