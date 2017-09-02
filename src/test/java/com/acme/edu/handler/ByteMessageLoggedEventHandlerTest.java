package com.acme.edu.handler;

import com.acme.edu.event.BooleanMessageLoggedEvent;
import com.acme.edu.event.ByteMessageLoggedEvent;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by vanbkin on 02.09.2017.
 */
public class ByteMessageLoggedEventHandlerTest {
    Saver saver = mock(Saver.class);
    Formatter formatter = mock(Formatter.class);

    @Test
    public void shouldHandleSimpleByteMessage() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("10", false));

        verify(formatter).format("primitive: 10");

    }

    @Test
    public void shouldHandleSeveralSubsequentByteMessages() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("10", true));
        sut.onEvent(new ByteMessageLoggedEvent("2", true));
        sut.onEvent(new ByteMessageLoggedEvent("3", false));

        verify(formatter).format("primitive: 15");
    }

    @Test
    public void shouldHandleMaxByteMessageOverflowInTheMiddle() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("10", true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MAX_VALUE), false));
        sut.onEvent(new ByteMessageLoggedEvent("3", false));

        verify(formatter).format("primitive: 10");
        verify(formatter).format("primitive: "+ String.valueOf(Byte.MAX_VALUE));
        verify(formatter).format("primitive: 3");

    }

    @Test
    public void shouldHandleMaxByteMessageOverflowAsLastInput() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("2", true));
        sut.onEvent(new ByteMessageLoggedEvent("10", true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MAX_VALUE), false));


        verify(formatter).format("primitive: 12");
        verify(formatter).format("primitive: "+ String.valueOf(Byte.MAX_VALUE));

    }

    @Test
    public void shouldHandleSubsequentMaxByteMessageOverflow() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("2", true));
        sut.onEvent(new ByteMessageLoggedEvent("10", true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MAX_VALUE), true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MAX_VALUE), false));


        verify(formatter).format("primitive: 12");
        verify(formatter, times(2)).format("primitive: "+ String.valueOf(Byte.MAX_VALUE));


    }

    @Test
    public void shouldHandleMinByteMessageOverflowInTheMiddle() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("-10", true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MIN_VALUE), false));
        sut.onEvent(new ByteMessageLoggedEvent("-3", false));

        verify(formatter).format("primitive: -10");
        verify(formatter).format("primitive: "+ String.valueOf(Byte.MIN_VALUE));
        verify(formatter).format("primitive: -3");

    }

    @Test
    public void shouldHandleMinByteMessageOverflowAsLastInput() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("-2", true));
        sut.onEvent(new ByteMessageLoggedEvent("-10", true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MIN_VALUE), false));


        verify(formatter).format("primitive: -12");
        verify(formatter).format("primitive: "+ String.valueOf(Byte.MIN_VALUE));

    }

    @Test
    public void shouldHandleSubsequentMinByteMessageOverflow() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("-2", true));
        sut.onEvent(new ByteMessageLoggedEvent("-10", true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MIN_VALUE), true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MIN_VALUE), false));


        verify(formatter).format("primitive: -12");
        verify(formatter, times(2)).format("primitive: "+ String.valueOf(Byte.MIN_VALUE));


    }

    @Test
    public void shouldHandleSubsequentNegativeAndPositiveByteMessages() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("-2", true));
        sut.onEvent(new ByteMessageLoggedEvent("10", true));
        sut.onEvent(new ByteMessageLoggedEvent("-10", true));
        sut.onEvent(new ByteMessageLoggedEvent("6", true));
        sut.onEvent(new ByteMessageLoggedEvent("-10", true));
        sut.onEvent(new ByteMessageLoggedEvent("-10", true));
        sut.onEvent(new ByteMessageLoggedEvent("16", false));

        verify(formatter).format("primitive: 0");


    }

    @Test
    public void shouldHandleSubsequentNegativeAndPositiveByteMessagesWithOverflow() throws Exception {
        ByteMessageLoggedEventHandler sut = new ByteMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ByteMessageLoggedEvent("-30", true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MAX_VALUE), true));
        sut.onEvent(new ByteMessageLoggedEvent("31", true));
        sut.onEvent(new ByteMessageLoggedEvent("10", true));
        sut.onEvent(new ByteMessageLoggedEvent("6", true));
        sut.onEvent(new ByteMessageLoggedEvent(String.valueOf(Byte.MIN_VALUE), true));
        sut.onEvent(new ByteMessageLoggedEvent("-18", true));
        sut.onEvent(new ByteMessageLoggedEvent("-10", false));

        verify(formatter).format("primitive: 97");
        verify(formatter).format("primitive: -109");



    }


}
