package com.acme.edu.Buffers;

import com.acme.edu.Messages.ByteMessage;
import com.acme.edu.Messages.IntMessage;
import com.acme.edu.Messages.LogMessage;
import com.acme.edu.Messages.StringMessage;

import java.util.ArrayList;
import java.util.List;

public class AggregationLogBuffer implements LogBuffer {
    private List<LogMessage> logMessageBuffer = new ArrayList<>();
    private LogBufferState logBufferState = LogBufferState.NO_BUFFER_STATE;
    private boolean stateSwitched = false;

    @Override
    public void addMessage(LogMessage logMessage) {
        switchLogBufferState(logMessage);
        if (stateSwitched) flushBuffer();
        logMessageBuffer.add(logMessage);
        if (logBufferState == LogBufferState.NO_BUFFER_STATE) flushBuffer();
        stateSwitched = false;
    }

    private void switchLogBufferState(LogMessage logMessage) {
        if (logMessage == null) return;
        LogBufferState previousLogBufferState = logBufferState;
        if (logMessage instanceof StringMessage) {
            setLogBufferState(LogBufferState.STRING_NEED_BUFFER_STATE);
        } else if (logMessage instanceof IntMessage) {
            setLogBufferState(LogBufferState.INT_NEED_BUFFER_STATE);
        } else if (logMessage instanceof ByteMessage) {
            setLogBufferState(LogBufferState.BYTE_NEED_BUFFER_STATE);
        } else {
            setLogBufferState(LogBufferState.NO_BUFFER_STATE);
        }
        stateSwitched = previousLogBufferState != logBufferState;
    }


    private void setLogBufferState(LogBufferState logBufferState) {
        this.logBufferState = logBufferState;
    }


    private void flushIntegersWithAggregation() {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        int aggregateValue = 0;
        for (LogMessage logMessage : logMessageBuffer) {
            int currentValue = Integer.valueOf(logMessage.getMessage());
            if (Integer.MAX_VALUE - aggregateValue < currentValue) {  // overFlow
                log(new IntMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            } else if (Integer.MIN_VALUE + aggregateValue > currentValue) {
                log(new IntMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            } else {
                aggregateValue += currentValue;
            }

        }
        log(new IntMessage(String.valueOf(aggregateValue)));
        logMessageBuffer.clear();
    }

    private void flushBytesWithAggregation() {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        int aggregateValue = 0;
        for (LogMessage logMessage : logMessageBuffer) {
            int currentValue = Byte.valueOf(logMessage.getMessage());
            if (Byte.MAX_VALUE - aggregateValue < currentValue) {  // overFlow
                log(new ByteMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            } else if (aggregateValue + currentValue < Byte.MIN_VALUE) {
                log(new ByteMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            } else {
                aggregateValue += currentValue;
            }

        }
        log(new ByteMessage(String.valueOf(aggregateValue)));
        logMessageBuffer.clear();
    }

    private void flushStringsWithAggregation() {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        String currentString = "";
        String previousString = logMessageBuffer.get(0).getMessage();
        int counter = 0;
        for (LogMessage logMessage : logMessageBuffer) {
            currentString = logMessage.getMessage();
            if (!currentString.equals(previousString)) {
                String numberOfStrings = counter > 1 ? " (x" + counter + ")" : "";
                log(new StringMessage(previousString + numberOfStrings));
                counter = 1;
                previousString = currentString;
            } else {
                counter++;
            }
        }
        String numberOfStrings = counter > 1 ? " (x" + counter + ")" : "";
        log(new StringMessage(currentString + numberOfStrings));

        logMessageBuffer.clear();

    }


    @Override
    public void flushBuffer() {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        if (logMessageBuffer.get(0) instanceof StringMessage) {
            flushStringsWithAggregation();
        } else if (logMessageBuffer.get(0) instanceof IntMessage) {
            flushIntegersWithAggregation();
        } else if (logMessageBuffer.get(0) instanceof ByteMessage) {
            flushBytesWithAggregation();
        } else {
            for (LogMessage logMessage : logMessageBuffer) {
                log(logMessage);
            }
        }

        logMessageBuffer.clear();
    }

    private void log(LogMessage message) {
        message.format();
        message.save();
    }
}
