package com.acme.edu.handler;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;
import com.acme.edu.event.BooleanMessageLoggedEvent;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BooleanMessageLoggedEventHandlerTest {
    @Test
    public void shouldAskFOrmatterToFormatBooleanMessageWithDescription() throws Exception {
        Saver saver = mock(Saver.class);
        Formatter formatter = mock(Formatter.class);
        BooleanMessageLoggedEventHandler sut = new BooleanMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new BooleanMessageLoggedEvent("true"));

        verify(formatter).format("primitive: true");

    }

}