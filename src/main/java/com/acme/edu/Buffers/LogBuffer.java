package com.acme.edu.Buffers;

import com.acme.edu.Messages.LogMessage;

import java.util.ArrayList;
import java.util.List;

public class LogBuffer {
    private List<LogMessage> logMessageBuffer = new ArrayList<>();
    private LogBufferInputState logBufferInputState = LogBufferInputState.INIT_STATE;
    private LogBufferInputState previousLogBufferInputState = logBufferInputState;
    private AggregationLogBufferFlusher aggregationLogBufferFlusher;

    public LogBuffer(AggregationLogBufferFlusher aggregationLogBufferFlusher) {
        this.aggregationLogBufferFlusher = aggregationLogBufferFlusher;
    }

    public void addMessage(LogMessage logMessage) {
        if (bufferStateChanged()) flushBuffer();
        logMessageBuffer.add(logMessage);
        if (bufferStateChanged() && !logBufferInputState.isBufferingOptionEnabled()) flushBuffer();
        resetBufferState();

    }

    private boolean bufferStateChanged() {
        return logBufferInputState != previousLogBufferInputState;
    }

    private void resetBufferState() {
        previousLogBufferInputState = logBufferInputState;
    }

    public void setLogBufferInputState(LogBufferInputState logBufferInputState) {
        this.logBufferInputState = logBufferInputState;
    }

    public void flushBuffer() {
        aggregationLogBufferFlusher.flushBuffer(logMessageBuffer);
    }

}
