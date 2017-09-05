package com.acme.edu;

import com.acme.edu.event.*;
import com.acme.edu.exception.LoggerBaseException;
import com.acme.edu.formatter.DefaultFormatter;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.EventDispatcher;
import com.acme.edu.saver.ConsoleSaver;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by vanbkin on 03.09.2017.
 */

public class ConsoleMessageEventHandlerTest {
    private Saver saver = mock(Saver.class);
    private Formatter formatter = mock(Formatter.class);
    private EventDispatcher eventDispatcher = mock(EventDispatcher.class);

    private ConsoleMessageEventHandler sut = new ConsoleMessageEventHandler(saver, formatter, eventDispatcher);


    @Test
    public void shouldChangeStateToSTRING_INPUTandSendToDispatcherStringMessageEvent() throws LoggerBaseException {

        String stringMessage = "1";
        sut.log(stringMessage);

        assertThat(sut.getMessageEventHandlerState().getCurrentState()).isEqualTo(State.STRING_INPUT);
        verify(eventDispatcher).dispatch(isA(StringMessageLoggedEvent.class));

    }

    @Test
    public void shouldChangeStateToINT_INPUTandSendToDispatcherIntMessageEvent() throws LoggerBaseException {

        int intMessage = 1;
        sut.log(intMessage);

        assertThat(sut.getMessageEventHandlerState().getCurrentState()).isEqualTo(State.INT_INPUT);
        verify(eventDispatcher).dispatch(isA(IntMessageLoggedEvent.class));

    }

    @Test
    public void shouldChangeStateToBYTE_INPUTandSendToDispatcheByteMessageEvent() throws LoggerBaseException {

        byte byteMessage = 1;
        sut.log(byteMessage);

        assertThat(sut.getMessageEventHandlerState().getCurrentState()).isEqualTo(State.BYTE_INPUT);
        verify(eventDispatcher).dispatch(isA(ByteMessageLoggedEvent.class));

    }

    @Test
    public void shouldChangeStateToCHAR_INPUTandSendToDispatcherCharMessageEvent() throws LoggerBaseException {

        char charMessge = 'a';
        sut.log(charMessge);

        assertThat(sut.getMessageEventHandlerState().getCurrentState()).isEqualTo(State.CHAR_INPUT);
        verify(eventDispatcher).dispatch(isA(CharMessageLoggedEvent.class));
    }

    @Test
    public void shouldChangeStateToBOOLEAN_INPUTandSendToDispatcherBooleanMessageEvent() throws LoggerBaseException {

        boolean booleanMessge = true;
        sut.log(booleanMessge);

        assertThat(sut.getMessageEventHandlerState().getCurrentState()).isEqualTo(State.BOOLEAN_INPUT);
        verify(eventDispatcher).dispatch(isA(BooleanMessageLoggedEvent.class));
    }

    @Test
    public void shouldChangeStateToOBJECT_INPUTandSendToDispatcherObjectMessageEvent() throws LoggerBaseException {

        Object objectMessage = new Object();
        sut.log(objectMessage);

        assertThat(sut.getMessageEventHandlerState().getCurrentState()).isEqualTo(State.OBJECT_INPUT);
        verify(eventDispatcher).dispatch(isA(ObjectMessageLoggedEvent.class));
    }

    @Test
    public void shouldSendFlushCacheEventWhenEndLogSessionCalled() throws LoggerBaseException {
        sut.endLogSession();

        verify(eventDispatcher).dispatch(isA(FlushCacheEvent.class));
    }

    @Test
    public void shouldSendFlushCacheEventEachTimeWhenInputStateChanges() throws LoggerBaseException {
        int intMessage = 1;
        byte byteMessage = 1;
        String stringMessage = "1";
        Object objectMessage = new Object();
        boolean booleanMessge = true;
        char charMessge = 'a';

        sut.log(intMessage);
        sut.log(byteMessage);
        sut.log(stringMessage);
        sut.log(objectMessage);
        sut.log(booleanMessge);
        sut.log(charMessge);

        verify(eventDispatcher, times(5)).dispatch(isA(FlushCacheEvent.class));
    }

    @Test
    public void shouldNotSendFlushCacheEventWhenInputStateChanges() throws LoggerBaseException {
        int intMessage1 = 1;
        int intMessage2 = 2;

        verify(eventDispatcher, times(0)).dispatch(isA(FlushCacheEvent.class));
    }



}
