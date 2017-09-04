package com.acme.edu.handler;

import com.acme.edu.event.BooleanMessageLoggedEvent;
import com.acme.edu.event.StringMessageLoggedEvent;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by vanbkin on 02.09.2017.
 */
public class StringMessageLoggerFacadeEventHandlerTest {
    Saver saver = mock(Saver.class);
    Formatter formatter = mock(Formatter.class);
    StringMessageLoggedEventHandler sut = new StringMessageLoggedEventHandler(saver, formatter);

    @Test
    public void shouldSendToFormatterSimpleMessageWithDescription() throws Exception {
        sut.onEvent(new StringMessageLoggedEvent("str 1", false));

        verify(formatter).format("string: str 1");

    }

    @Test
    public void shouldSendToFormatterSeveralSimpleMessagesWithDescription() throws Exception {
        sut.onEvent(new StringMessageLoggedEvent("str 1", true));
        sut.onEvent(new StringMessageLoggedEvent("str 2", true));
        sut.onEvent(new StringMessageLoggedEvent("str 3", true));
        sut.onEvent(new StringMessageLoggedEvent("str 4", false));

        verify(formatter).format("string: str 1");
        verify(formatter).format("string: str 2");
        verify(formatter).format("string: str 3");
        verify(formatter).format("string: str 4");

    }

    @Test
    public void shouldSendToFormatterSeveralSimpleAggregatedMessagesWithDescription() throws Exception {
        sut.onEvent(new StringMessageLoggedEvent("str 1", true));
        sut.onEvent(new StringMessageLoggedEvent("str 1", true));
        sut.onEvent(new StringMessageLoggedEvent("str 1", true));
        sut.onEvent(new StringMessageLoggedEvent("str 1", false));

        verify(formatter).format("string: str 1 (x4)");


    }


    @Test
    public void shouldSendToFormatterSeveralAggregatedMessagesWithDescription() throws Exception {
        sut.onEvent(new StringMessageLoggedEvent("str 1", true));
        sut.onEvent(new StringMessageLoggedEvent("str 1", true));
        sut.onEvent(new StringMessageLoggedEvent("str 3", true));
        sut.onEvent(new StringMessageLoggedEvent("str 4", true));
        sut.onEvent(new StringMessageLoggedEvent("str 4", true));
        sut.onEvent(new StringMessageLoggedEvent("str 4", true));
        sut.onEvent(new StringMessageLoggedEvent("str 1", false));

        verify(formatter).format("string: str 1 (x2)");
        verify(formatter).format("string: str 3");
        verify(formatter).format("string: str 4 (x3)");
        verify(formatter).format("string: str 1");

    }
}
