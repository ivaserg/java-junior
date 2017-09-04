package com.acme.edu.handler;

import com.acme.edu.event.ObjectMessageLoggedEvent;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ObjectMessageLoggedEventHandlerTest {

    Saver saver = mock(Saver.class);

    Formatter formatter = mock(Formatter.class);
    ObjectMessageLoggedEventHandler sut = new ObjectMessageLoggedEventHandler(saver, formatter);

    @Test
    public void shouldAskFormatterToFormatObjectMessageWithDescription() throws Exception {
        sut.onEvent(new ObjectMessageLoggedEvent("dummy"));

        verify(formatter).format("reference: dummy");

    }

}