package com.acme.edu.event;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ByteMessageLoggedEventTest {
    @Test
    public void getMessage() throws Exception {
        ByteMessageLoggedEvent byteMessageLoggedEvent = new ByteMessageLoggedEvent("10", true);

        assertThat(byteMessageLoggedEvent.getMessage()).isEqualTo("10");

    }

}