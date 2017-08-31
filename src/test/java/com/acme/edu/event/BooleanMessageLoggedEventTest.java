package com.acme.edu.event;


import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class BooleanMessageLoggedEventTest {

    @Test
    public void testGetMessage() {

        BooleanMessageLoggedEvent booleanMessageLoggedEvent = new BooleanMessageLoggedEvent("true");


        assertThat(booleanMessageLoggedEvent.getMessage()).isEqualTo("true");

    }

}