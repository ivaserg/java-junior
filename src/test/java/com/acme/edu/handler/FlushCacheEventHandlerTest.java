package com.acme.edu.handler;

import com.acme.edu.MessageEventHandlerState;
import com.acme.edu.State;
import com.acme.edu.event.ByteMessageLoggedEvent;
import com.acme.edu.event.FlushCacheEvent;
import com.acme.edu.event.IntMessageLoggedEvent;
import com.acme.edu.event.StringMessageLoggedEvent;
import com.acme.edu.exception.LoggerBaseException;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.Event;
import com.acme.edu.framework.EventDispatcher;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FlushCacheEventHandlerTest {
    Saver saver = mock(Saver.class);
    Formatter formatter = mock(Formatter.class);
    EventDispatcher dispatcher = mock(EventDispatcher.class);
    FlushCacheEventHandler sut = new FlushCacheEventHandler(saver, formatter, dispatcher);
    MessageEventHandlerState messageEventHandlerState = new MessageEventHandlerState();

    @Test
    public void shouldCallDispatcherToFlushAggregatedIntMessages() throws LoggerBaseException  {
        FlushCacheEvent flushCacheEvent = new FlushCacheEvent(messageEventHandlerState);
        messageEventHandlerState.switchState(State.INT_INPUT);
        sut.onEvent(flushCacheEvent);

        verify(dispatcher, times(1)).dispatch(isA(IntMessageLoggedEvent.class));

    }

    @Test
    public void shouldCallDispatcherToFlushAggregatedStringMessages() throws LoggerBaseException {
        FlushCacheEvent flushCacheEvent = new FlushCacheEvent(messageEventHandlerState);
        messageEventHandlerState.switchState(State.STRING_INPUT);
        sut.onEvent(flushCacheEvent);

        verify(dispatcher, times(1)).dispatch(isA(StringMessageLoggedEvent.class));

    }

    @Test
    public void shouldCallDispatcherToFlushAggregatedByteMessages() throws LoggerBaseException {
        FlushCacheEvent flushCacheEvent = new FlushCacheEvent(messageEventHandlerState);
        messageEventHandlerState.switchState(State.BYTE_INPUT);
        sut.onEvent(flushCacheEvent);

        verify(dispatcher, times(1)).dispatch(isA(ByteMessageLoggedEvent.class));

    }

    @Test
    public void shouldDoNothingWhenStateChangedFromOtheStatesWhichDontNeedAggregation() throws LoggerBaseException {
        FlushCacheEvent flushCacheEvent = new FlushCacheEvent(messageEventHandlerState);
        messageEventHandlerState.switchState(State.OBJECT_INPUT);
        sut.onEvent(flushCacheEvent);
        messageEventHandlerState.switchState(State.BOOLEAN_INPUT);
        sut.onEvent(flushCacheEvent);
        messageEventHandlerState.switchState(State.CHAR_INPUT);
        sut.onEvent(flushCacheEvent);

        verify(dispatcher, times(0)).dispatch(isA(Event.class));

    }



}
