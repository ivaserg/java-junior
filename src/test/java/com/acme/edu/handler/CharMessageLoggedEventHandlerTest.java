package com.acme.edu.handler;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;
import com.acme.edu.event.CharMessageLoggedEvent;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CharMessageLoggedEventHandlerTest {
    @Test
    public void shouldAskFOrmatterToFormatBooleanMessageWithDescription() throws Exception {
        Saver saver = mock(Saver.class);
        Formatter formatter = mock(Formatter.class);
        CharMessageLoggedEventHandler sut = new CharMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new CharMessageLoggedEvent("A"));

        verify(formatter).format("char: A");

    }

}