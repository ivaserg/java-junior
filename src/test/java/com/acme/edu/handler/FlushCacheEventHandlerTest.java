package com.acme.edu.handler;

import com.acme.edu.State;
import com.acme.edu.event.ByteMessageLoggedEvent;
import com.acme.edu.event.FlushCacheEvent;
import com.acme.edu.event.IntMessageLoggedEvent;
import com.acme.edu.event.StringMessageLoggedEvent;
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

    @Test
    public void shouldCallDispatcherToFlushAggregatedIntMessages() {
        FlushCacheEvent flushCacheEvent = new FlushCacheEvent(State.INT_INPUT);
        sut.onEvent(flushCacheEvent);

        verify(dispatcher, times(1)).dispatch(isA(IntMessageLoggedEvent.class));

    }

    @Test
    public void shouldCallDispatcherToFlushAggregatedStringMessages() {
        FlushCacheEvent flushCacheEvent = new FlushCacheEvent(State.STRING_INPUT);
        sut.onEvent(flushCacheEvent);

        verify(dispatcher, times(1)).dispatch(isA(StringMessageLoggedEvent.class));

    }

    @Test
    public void shouldCallDispatcherToFlushAggregatedByteMessages() {
        FlushCacheEvent flushCacheEvent = new FlushCacheEvent(State.BYTE_INPUT);
        sut.onEvent(flushCacheEvent);

        verify(dispatcher, times(1)).dispatch(isA(ByteMessageLoggedEvent.class));

    }

    @Test
    public void shouldDoNothingWhenStateChangedFromOtheStatesWhichDontNeedAggregation() {
        FlushCacheEvent flushCacheEvent = new FlushCacheEvent(State.OBJECT_INPUT);
        sut.onEvent(flushCacheEvent);
        flushCacheEvent = new FlushCacheEvent(State.BOOLEAN_INPUT);
        sut.onEvent(flushCacheEvent);
        flushCacheEvent = new FlushCacheEvent(State.CHAR_INPUT);
        sut.onEvent(flushCacheEvent);

        verify(dispatcher, times(0)).dispatch(isA(Event.class));

    }



}
