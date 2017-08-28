package com.acme.edu.Buffers;

import com.acme.edu.Messages.LogMessage;

public interface LogBuffer {
    void addMessage(LogMessage logMessage);

    void flushBuffer();


}
