package com.acme.edu;


import com.acme.edu.event.*;
import com.acme.edu.exception.IllegalInputMessageException;
import com.acme.edu.exception.MessageHandlingException;
import com.acme.edu.formatter.DefaultFormatter;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.Event;
import com.acme.edu.framework.EventDispatcher;
import com.acme.edu.handler.*;
import com.acme.edu.saver.ConsoleSaver;
import com.acme.edu.saver.Saver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConsoleMessageEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleMessageEventHandler.class);

    private MessageEventHandlerState messageEventHandlerState = new MessageEventHandlerState();
    private EventDispatcher dispatcher;
    private Saver saver;
    private Formatter formatter;

    public ConsoleMessageEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
        dispatcher = new EventDispatcher();
        initDispatcher();
    }

    public ConsoleMessageEventHandler(Saver saver, Formatter formatter, EventDispatcher eventDispatcher) {
        this.saver = saver;
        this.formatter = formatter;
        this.dispatcher = eventDispatcher;
    }

    public void initDispatcher() {
        dispatcher.registerHandler(BooleanMessageLoggedEvent.class, new BooleanMessageLoggedEventHandler(saver, formatter));
        dispatcher.registerHandler(CharMessageLoggedEvent.class, new CharMessageLoggedEventHandler(saver, formatter));
        dispatcher.registerHandler(ObjectMessageLoggedEvent.class, new ObjectMessageLoggedEventHandler(saver, formatter));
        dispatcher.registerHandler(IntMessageLoggedEvent.class, new IntMessageLoggedEventHandler(saver, formatter));
        dispatcher.registerHandler(ByteMessageLoggedEvent.class, new ByteMessageLoggedEventHandler(saver, formatter));
        dispatcher.registerHandler(StringMessageLoggedEvent.class, new StringMessageLoggedEventHandler(saver, formatter));
        dispatcher.registerHandler(FlushCacheEvent.class, new FlushCacheEventHandler(saver, formatter, dispatcher));
    }

    public void endLogSession() {
        messageEventHandlerState.switchState(messageEventHandlerState.getPreviousState());
        flushCache();
    }


    public void flushCache()  {
        dispatchEvent(new FlushCacheEvent(messageEventHandlerState));
    }


    public void log(int message) {
        messageEventHandlerState.switchState(State.INT_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new IntMessageLoggedEvent(String.valueOf(message), true));

    }


    public void log(byte message) {
        messageEventHandlerState.switchState(State.BYTE_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new ByteMessageLoggedEvent(String.valueOf(message), true));
    }


    public void log(char message) {
        messageEventHandlerState.switchState(State.CHAR_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new CharMessageLoggedEvent(String.valueOf(message)));
    }


    public void log(String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalInputMessageException("Input string message cannot be null or empty.");
        }
        messageEventHandlerState.switchState(State.STRING_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new StringMessageLoggedEvent(message, true));
    }


    public void log(boolean message) {
        messageEventHandlerState.switchState(State.BOOLEAN_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new BooleanMessageLoggedEvent(Boolean.toString(message)));

    }


    public void log(Object message)  {
        if (message == null) return;
        messageEventHandlerState.switchState(State.OBJECT_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new ObjectMessageLoggedEvent(message.toString()));
    }

    public MessageEventHandlerState getMessageEventHandlerState() {
        return messageEventHandlerState;
    }

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    private void dispatchEvent(Event event)  {
        try {
            dispatcher.dispatch(event);
        } catch (MessageHandlingException e) {
           e.printStackTrace();
        }
    }


}
