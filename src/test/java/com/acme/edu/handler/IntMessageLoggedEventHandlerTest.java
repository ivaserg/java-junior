package com.acme.edu.handler;

import com.acme.edu.event.BooleanMessageLoggedEvent;
import com.acme.edu.event.IntMessageLoggedEvent;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by vanbkin on 02.09.2017.
 */
public class IntMessageLoggedEventHandlerTest {
    Saver saver = mock(Saver.class);
    Formatter formatter = mock(Formatter.class);

    @Test
    public void shouldHandleSimpleIntMessage() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("10", false));

        verify(formatter).format("primitive: 10");

    }

    @Test
    public void shouldHandleSeveralSubsequentIntMessages() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("10", true));
        sut.onEvent(new IntMessageLoggedEvent("2", true));
        sut.onEvent(new IntMessageLoggedEvent("3", false));

        verify(formatter).format("primitive: 15");
    }

    @Test
    public void shouldHandleMaxIntMessageOverflowInTheMiddle() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("10", true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MAX_VALUE), false));
        sut.onEvent(new IntMessageLoggedEvent("3", false));

        verify(formatter).format("primitive: 10");
        verify(formatter).format("primitive: "+ String.valueOf(Integer.MAX_VALUE));
        verify(formatter).format("primitive: 3");

    }

    @Test
    public void shouldHandleMaxIntMessageOverflowAsLastInput() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("2", true));
        sut.onEvent(new IntMessageLoggedEvent("10", true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MAX_VALUE), false));


        verify(formatter).format("primitive: 12");
        verify(formatter).format("primitive: "+ String.valueOf(Integer.MAX_VALUE));

    }

    @Test
    public void shouldHandleSubsequentMaxIntMessageOverflow() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("2", true));
        sut.onEvent(new IntMessageLoggedEvent("10", true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MAX_VALUE), true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MAX_VALUE), false));


        verify(formatter).format("primitive: 12");
        verify(formatter, times(2)).format("primitive: "+ String.valueOf(Integer.MAX_VALUE));


    }

    @Test
    public void shouldHandleMinIntMessageOverflowInTheMiddle() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("-10", true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MIN_VALUE), false));
        sut.onEvent(new IntMessageLoggedEvent("-3", false));

        verify(formatter).format("primitive: -10");
        verify(formatter).format("primitive: "+ String.valueOf(Integer.MIN_VALUE));
        verify(formatter).format("primitive: -3");

    }

    @Test
    public void shouldHandleMinIntMessageOverflowAsLastInput() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("-2", true));
        sut.onEvent(new IntMessageLoggedEvent("-10", true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MIN_VALUE), false));


        verify(formatter).format("primitive: -12");
        verify(formatter).format("primitive: "+ String.valueOf(Integer.MIN_VALUE));

    }

    @Test
    public void shouldHandleSubsequentMinIntMessageOverflow() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("-2", true));
        sut.onEvent(new IntMessageLoggedEvent("-10", true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MIN_VALUE), true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MIN_VALUE), false));


        verify(formatter).format("primitive: -12");
        verify(formatter, times(2)).format("primitive: "+ String.valueOf(Integer.MIN_VALUE));


    }

    @Test
    public void shouldHandleSubsequentNegativeAndPositiveIntMessages() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("-2", true));
        sut.onEvent(new IntMessageLoggedEvent("10", true));
        sut.onEvent(new IntMessageLoggedEvent("-10", true));
        sut.onEvent(new IntMessageLoggedEvent("6", true));
        sut.onEvent(new IntMessageLoggedEvent("-10", true));
        sut.onEvent(new IntMessageLoggedEvent("-10", true));
        sut.onEvent(new IntMessageLoggedEvent("16", false));

        verify(formatter).format("primitive: 0");


    }

    @Test
    public void shouldHandleSubsequentNegativeAndPositiveIntMessagesWithOverflow() throws Exception {
        IntMessageLoggedEventHandler sut = new IntMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new IntMessageLoggedEvent("-30", true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MAX_VALUE), true));
        sut.onEvent(new IntMessageLoggedEvent("31", true));
        sut.onEvent(new IntMessageLoggedEvent("10", true));
        sut.onEvent(new IntMessageLoggedEvent("6", true));
        sut.onEvent(new IntMessageLoggedEvent(String.valueOf(Integer.MIN_VALUE), true));
        sut.onEvent(new IntMessageLoggedEvent("-18", true));
        sut.onEvent(new IntMessageLoggedEvent("-10", false));

        verify(formatter).format("primitive: 2147483617");
        verify(formatter).format("primitive: -2147483629");



    }


}
