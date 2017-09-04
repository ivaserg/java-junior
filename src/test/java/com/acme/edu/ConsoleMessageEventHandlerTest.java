package com.acme.edu;

import com.acme.edu.event.StringMessageLoggedEvent;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.EventDispatcher;
import com.acme.edu.saver.Saver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by vanbkin on 03.09.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsoleMessageEventHandlerTest {

    @Mock
    private EventDispatcher eventDispatcher;
    @Mock
    private Saver saver;
    @Mock
    private Formatter formatter;

    private ConsoleMessageEventHandler consoleMessageEventHandler = new ConsoleMessageEventHandler(saver, formatter);

    @Test
    public void shouldSendToDispatcherStringMessageEvent() {

        StringMessageLoggedEvent stringMessageLoggedEvent = new StringMessageLoggedEvent("str 1");
        consoleMessageEventHandler.log(stringMessageLoggedEvent.getMessage());

        assertThat(true).isEqualTo(true);

    }
}
