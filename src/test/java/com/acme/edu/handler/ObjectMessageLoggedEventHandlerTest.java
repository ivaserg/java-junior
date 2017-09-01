package com.acme.edu.handler;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import com.acme.edu.event.ObjectMessageLoggedEvent;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ObjectMessageLoggedEventHandlerTest {
    @Test
    public void shouldAskFormatterToFormatObjectMessageWithDescription() throws Exception {
        Saver saver = mock(Saver.class);
        Formatter formatter = mock(Formatter.class);
        ObjectMessageLoggedEventHandler sut = new ObjectMessageLoggedEventHandler(saver, formatter);

        sut.onEvent(new ObjectMessageLoggedEvent("dummy"));

        verify(formatter).format("reference: dummy");

    }

}