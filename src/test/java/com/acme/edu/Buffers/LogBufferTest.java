package com.acme.edu.Buffers;

import com.acme.edu.Messages.IntMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.mock;

public class LogBufferTest {
    @Before
    public void setUp() {
        AggregationLogBufferFlusher aggregationLogBufferFlusher = mock(AggregationLogBufferFlusher.class);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void assertBefferContainsIntMessageWhenAddedAndStateChanged() {
        LogBuffer sut = new LogBuffer(new AggregationLogBufferFlusher());

        sut.addMessage(new IntMessage("9"));


    }
}
