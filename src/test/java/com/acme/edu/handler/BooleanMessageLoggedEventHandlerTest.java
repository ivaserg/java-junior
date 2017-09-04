package com.acme.edu.handler;

import com.acme.edu.event.BooleanMessageLoggedEvent;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BooleanMessageLoggedEventHandlerTest {
    Saver saver = mock(Saver.class);
    Formatter formatter = mock(Formatter.class);
    BooleanMessageLoggedEventHandler sut = new BooleanMessageLoggedEventHandler(saver, formatter);

    @Test
    public void shouldAskFOrmatterToFormatBooleanMessageWithDescription() throws Exception {
        sut.onEvent(new BooleanMessageLoggedEvent("true"));

        verify(formatter).format("primitive: true");

    }

}