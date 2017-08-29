package com.acme.edu.Buffers;

import com.acme.edu.Messages.IntMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogBufferTest {
    @Before
    void setUp() {

    }

    @After
    void tearDown() {

    }

    @Test
    void assertBefferContainsIntMessageWhenAddedAndStateChanged() {
        LogBuffer logBuffer = new LogBuffer(new AggregationLogBufferFlusher());

        logBuffer.addMessage(new IntMessage("Int"));


    }
}
