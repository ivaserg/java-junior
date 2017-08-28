package com.acme.edu.Buffers;

import com.acme.edu.Messages.LogMessage;

import java.util.List;

public interface LogBuffer {
    void addMessage(LogMessage logMessage);

    void flushBuffer();

    void setLogBufferInputState(LogBufferInputState logBufferInputState);

}
