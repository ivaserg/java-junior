package com.acme.edu.handler;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;
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
    public void onEvent(FlushCacheEvent event) {
        State state = event.getPreviousState();
        switch (state) {
            case INT_INPUT:
                dispatcher.dispatch(new IntMessageLoggedEvent("0", false));
                break;
            case BYTE_INPUT:
                dispatcher.dispatch(new ByteMessageLoggedEvent("0", false));
                break;
            case STRING_INPUT: // TO DO
                dispatcher.dispatch(new StringMessageLoggedEvent("", false));
                break;


        }

    }
}
