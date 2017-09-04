package com.acme.edu;


import com.acme.edu.event.*;
import com.acme.edu.exception.IllegalInputMessageException;
import com.acme.edu.exception.LoggerBaseException;
import com.acme.edu.exception.MessageHandlingException;
import com.acme.edu.formatter.DefaultFormatter;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.Event;
import com.acme.edu.framework.EventDispatcher;
import com.acme.edu.handler.*;
import com.acme.edu.saver.ConsoleSaver;
import com.acme.edu.saver.Saver;

public class ConsoleMessageEventHandler {
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

    public void endLogSession() throws LoggerBaseException {
        messageEventHandlerState.switchState(messageEventHandlerState.getPreviousState());
        flushCache();
    }


    public void flushCache() throws LoggerBaseException {
        dispatchEvent(new FlushCacheEvent(messageEventHandlerState));
    }


    public void log(int message) throws LoggerBaseException {
        messageEventHandlerState.switchState(State.INT_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        try {
            dispatchEvent(new IntMessageLoggedEvent(String.valueOf(message), true));
        } catch (LoggerBaseException e) {
            e.printStackTrace();
        }
    }


    public void log(byte message) throws LoggerBaseException {
        messageEventHandlerState.switchState(State.BYTE_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new ByteMessageLoggedEvent(String.valueOf(message), true));
    }


    public void log(char message) throws LoggerBaseException {
        messageEventHandlerState.switchState(State.CHAR_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new CharMessageLoggedEvent(String.valueOf(message)));
    }


    public void log(String message) throws LoggerBaseException {
        if (message == null || message.isEmpty()) {
            throw new IllegalInputMessageException("Input string message cannot be null or empty.");
        }
        messageEventHandlerState.switchState(State.STRING_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new StringMessageLoggedEvent(message, true));
    }


    public void log(boolean message) throws LoggerBaseException {
        messageEventHandlerState.switchState(State.BOOLEAN_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatchEvent(new BooleanMessageLoggedEvent(Boolean.toString(message)));

    }


    public void log(Object message) throws LoggerBaseException{
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

    private void dispatchEvent(Event event) throws LoggerBaseException {
        try {
            dispatcher.dispatch(event);
        } catch (MessageHandlingException e) {
            throw new LoggerBaseException("Failed to dispatch message", e.getCause());
        }
    }

    public static void main(String[] args) throws LoggerBaseException {
        ConsoleMessageEventHandler consoleMessageEventHandler = new ConsoleMessageEventHandler(new ConsoleSaver(), new DefaultFormatter());
        consoleMessageEventHandler.log(true);
    }
}
