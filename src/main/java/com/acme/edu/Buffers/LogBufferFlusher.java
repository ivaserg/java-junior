package com.acme.edu.Buffers;

import com.acme.edu.Messages.ByteMessage;
import com.acme.edu.Messages.IntMessage;
import com.acme.edu.Messages.LogMessage;
import com.acme.edu.Messages.StringMessage;

import java.util.List;

/**
 * Created by vanbkin on 28.08.2017.
 */
public class LogBufferFlusher {

    public void flushBuffer(List<LogMessage> logMessageBuffer) {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        if (logMessageBuffer.get(0) instanceof StringMessage && LogBufferInputState.STRING_INPUT.isBufferingOptionEnabled()) {
            flushStringsWithAggregation(logMessageBuffer);
//        } else if (logMessageBuffer.get(0) instanceof IntMessage && LogBufferInputState.INT_INPUT.isBufferingOptionEnabled()) {
//            flushIntegersWithAggregation(logMessageBuffer);
        } else if (logMessageBuffer.get(0) instanceof ByteMessage && LogBufferInputState.BYTE_INPUT.isBufferingOptionEnabled()) {
            flushBytesWithAggregation(logMessageBuffer);
        } else {
            for (LogMessage logMessage : logMessageBuffer) {
                flush(logMessage);
            }
        }
        logMessageBuffer.clear();
    }


    private void flushIntegersWithAggregation(List<LogMessage> logMessageBuffer) {
//        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
//        int aggregateValue = 0;
//        for (LogMessage logMessage : logMessageBuffer) {
//            int currentValue = Integer.valueOf(logMessage.getMessage());
//            if (Integer.MAX_VALUE - aggregateValue < currentValue) {  // overFlow
//                flush(new IntMessage(String.valueOf(aggregateValue)));
//                aggregateValue = currentValue;
//            } else if (Integer.MIN_VALUE + aggregateValue > currentValue) {
//                flush(new IntMessage(String.valueOf(aggregateValue)));
//                aggregateValue = currentValue;
//            } else {
//                aggregateValue += currentValue;
//            }
//
//        }
//        flush(new IntMessage(String.valueOf(aggregateValue)));
//        logMessageBuffer.clear();
    }

    private void flushBytesWithAggregation(List<LogMessage> logMessageBuffer) {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        int aggregateValue = 0;
        for (LogMessage logMessage : logMessageBuffer) {
            int currentValue = Byte.valueOf(logMessage.getMessage());
            if (Byte.MAX_VALUE - aggregateValue < currentValue) {  // overFlow
                flush(new ByteMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            } else if (aggregateValue + currentValue < Byte.MIN_VALUE) {
                flush(new ByteMessage(String.valueOf(aggregateValue)));
                aggregateValue = currentValue;
            } else {
                aggregateValue += currentValue;
            }

        }
        flush(new ByteMessage(String.valueOf(aggregateValue)));
        logMessageBuffer.clear();
    }

    private void flushStringsWithAggregation(List<LogMessage> logMessageBuffer) {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        String currentString = "";
        String previousString = logMessageBuffer.get(0).getMessage();
        int counter = 0;
        for (LogMessage logMessage : logMessageBuffer) {
            currentString = logMessage.getMessage();
            if (!currentString.equals(previousString)) {
                String numberOfStrings = counter > 1 ? " (x" + counter + ")" : "";
                flush(new StringMessage(previousString + numberOfStrings));
                counter = 1;
                previousString = currentString;
            } else {
                counter++;
            }
        }
        String numberOfStrings = counter > 1 ? " (x" + counter + ")" : "";
        flush(new StringMessage(currentString + numberOfStrings));

        logMessageBuffer.clear();

    }

    private void flush(LogMessage message) {
        message.format();
        message.save();
    }

}
