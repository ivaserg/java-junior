package com.acme.edu;

import com.acme.edu.Messages.ByteMessage;
import com.acme.edu.Messages.IntMessage;
import com.acme.edu.Messages.LogMessage;
import com.acme.edu.Messages.StringMessage;

import java.util.ArrayList;
import java.util.List;

public class LogBuffer {
    private List<LogMessage> logMessageBuffer = new ArrayList<>();

    public void addMessage(LogMessage logMessage) {
        if (logMessage == null) return;
        logMessageBuffer.add(logMessage);
    }

    private void flushIntegers() {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        int aggregateValue = 0;
        for (LogMessage logMessage : logMessageBuffer) {
            int currentValue = Integer.valueOf(logMessage.getMessage());
            if (Integer.MAX_VALUE - aggregateValue < currentValue) {  // overFlow
                log(new IntMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            } else if (Integer.MIN_VALUE + aggregateValue > currentValue){
                log(new IntMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            } else {
                aggregateValue += currentValue;
            }

        }
        log(new IntMessage(String.valueOf(aggregateValue)));
        logMessageBuffer.clear();
    }

    private void flushBytes() {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        int aggregateValue = 0;
        for (LogMessage logMessage : logMessageBuffer) {
            int currentValue = Byte.valueOf(logMessage.getMessage());
            if (Byte.MAX_VALUE - aggregateValue < currentValue) {  // overFlow
                log(new ByteMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            } else if (Byte.MIN_VALUE + currentValue < Byte.MIN_VALUE){
                log(new ByteMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            }
            else {
                aggregateValue += currentValue;
            }

        }
        log(new ByteMessage(String.valueOf(aggregateValue)));
        logMessageBuffer.clear();
    }

    private void flushStrings() {
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

    public void flushMessagesDependingOnState(LoggerState state) {
        switch (state) {
            case INT_BUFFER_STATE:
                flushIntegers();
                break;
            case BYTE_BUFFER_STATE:
                flushBytes();
                break;
            case STRING_BUFFER_STATE:
                flushStrings();
                break;
            case NO_BUFFER_STATE:
                flushBuffer();
                break;
        }
    }

    public void flushBuffer() {
        if (logMessageBuffer==null || logMessageBuffer.size()==0) return;
        for (LogMessage logMessage: logMessageBuffer) {
            log(logMessage);
        }
        logMessageBuffer.clear();
    }

    private void log(LogMessage message) {
        message.enrichWithTypeDescription();
        message.format();
        message.save();
    }
}
