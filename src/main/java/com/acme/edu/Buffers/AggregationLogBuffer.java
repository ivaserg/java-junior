package com.acme.edu.Buffers;

import com.acme.edu.Messages.LogMessage;

import java.util.ArrayList;
import java.util.List;

public class AggregationLogBuffer implements LogBuffer {
    private List<LogMessage> logMessageBuffer = new ArrayList<>();
    private LogBufferInputState logBufferInputState = LogBufferInputState.INIT_STATE;
    private LogBufferInputState previousLogBufferInputState = logBufferInputState;
    private LogBufferFlusher logBufferFlusher;

    public AggregationLogBuffer(LogBufferFlusher logBufferFlusher) {
        this.logBufferFlusher = logBufferFlusher;
    }

    @Override
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

    @Override
    public void setLogBufferInputState(LogBufferInputState logBufferInputState) {
        this.logBufferInputState = logBufferInputState;
    }

    @Override
    public void flushBuffer() {
        logBufferFlusher.flushBuffer(logMessageBuffer);
    }

}
