package com.acme.edu;


import com.acme.edu.event.*;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.EventDispatcher;
import com.acme.edu.handler.*;
import com.acme.edu.saver.Saver;

public class ConsoleMessageEventHandler implements MessageEventHandler {
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


    public void flushCache() {
        dispatcher.dispatch(new FlushCacheEvent(messageEventHandlerState.getPreviousState()));
    }

    @Override
    public void handleEvent(Object message) {
        if (message instanceof Integer) {
            log((int) message);
        } else if (message instanceof Byte) {
            log((byte) message);
        } else if (message instanceof Character) {
            log((char) message);
        } else if (message instanceof String) {
            log((String) message);
        } else if (message instanceof Boolean) {
            log((boolean) message);
        } else {
            log(message);
        }
    }


    public void log(int message) {
        messageEventHandlerState.switchState(State.INT_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatcher.dispatch(new IntMessageLoggedEvent(String.valueOf(message), true));
    }


    public void log(byte message) {
        messageEventHandlerState.switchState(State.BYTE_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatcher.dispatch(new ByteMessageLoggedEvent(String.valueOf(message), true));
    }


    public void log(char message) {
        messageEventHandlerState.switchState(State.CHAR_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatcher.dispatch(new CharMessageLoggedEvent(String.valueOf(message)));
    }


    public void log(String message) {
        if (message == null) return;
        messageEventHandlerState.switchState(State.STRING_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatcher.dispatch(new StringMessageLoggedEvent(message, true));
    }


    public void log(boolean message) {
        messageEventHandlerState.switchState(State.BOOLEAN_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatcher.dispatch(new BooleanMessageLoggedEvent(Boolean.toString(message)));

    }


    public void log(Object message) {
        if (message == null) return;
        messageEventHandlerState.switchState(State.OBJECT_INPUT);
        if (messageEventHandlerState.isStateSwitched()) {
            flushCache();
        }
        dispatcher.dispatch(new ObjectMessageLoggedEvent(message.toString()));
    }

    public MessageEventHandlerState getMessageEventHandlerState() {
        return messageEventHandlerState;
    }

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

}
