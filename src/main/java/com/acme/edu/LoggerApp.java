package com.acme.edu;


import com.acme.edu.Formatters.DefaultFormatter;
import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Messages.*;
import com.acme.edu.Savers.ConsoleSaver;
import com.acme.edu.Savers.Saver;

import java.util.ArrayList;
import java.util.List;

public class LoggerApp {
    private LoggerState currentLoggerState = LoggerState.NO_BUFFER_STATE;
    private List<LogMessage> logMessageBuffer = new ArrayList<>();

    public LoggerApp() {
        LogMessage.saver = new ConsoleSaver();
        LogMessage.formatter =  new DefaultFormatter();
    }

    public LoggerApp(Saver saver, Formatter formatter) {
        LogMessage.saver = saver;
        LogMessage.formatter = formatter;
    }

    private void resetState() {
        currentLoggerState = LoggerState.NO_BUFFER_STATE;
    }


    private void changeState(LoggerState newLoggerState) {
        if (currentLoggerState != LoggerState.NO_BUFFER_STATE && currentLoggerState != newLoggerState) {
            flushMessages();
            resetState();
        }
        currentLoggerState = newLoggerState;
    }

    private void flushIntegers() {
        if (logMessageBuffer == null || logMessageBuffer.size() == 0) return;
        int aggregateValue = 0;
        for (LogMessage logMessage : logMessageBuffer) {
            int currentValue = Integer.valueOf(logMessage.getMessage());
            if (Integer.MAX_VALUE - aggregateValue < currentValue) {  // overFlow
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
            } else {
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

    public void endLogSession() {
        flushMessages();
        resetState();
    }

    private void flushMessages() {
        switch (currentLoggerState) {
            case INT_BUFFER_STATE:
                flushIntegers();
                break;
            case BYTE_BUFFER_STATE:
                flushBytes();
                break;
            case STRING_BUFFER_STATE:
                flushStrings();
                break;
        }
    }

    public void log(int message) {
        changeState(LoggerState.INT_BUFFER_STATE);
        logMessageBuffer.add(new IntMessage(Integer.toString(message)));
    }


    public void log(byte message) {
        changeState(LoggerState.BYTE_BUFFER_STATE);
        logMessageBuffer.add(new ByteMessage(Byte.toString(message)));
    }

    public void log(char message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        log(new CharMessage(Character.toString(message)));
        resetState();
    }

    public void log(String message) {
        if (message == null) return;
        changeState(LoggerState.STRING_BUFFER_STATE);
        logMessageBuffer.add(new StringMessage(message));
    }


    public void log(boolean message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        log(new BooleanMessage(Boolean.toString(message)));
        resetState();
    }

    public void log(Object message) {
        if (message == null) return;
        changeState(LoggerState.NO_BUFFER_STATE);
        log(new ObjectMessage(message.toString()));
        resetState();
    }

    public void log(int[] message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        String enrichedMessage = EnrichmentUtils.enrichOneDimensionalArray(message);
        log(new ArrayMessage(enrichedMessage));
        resetState();
    }


    public void log(int[][] message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        String enrichedMessage = EnrichmentUtils.enrichTwoDimensionalArray(message);
        log(new TwoDimArrayMessage(enrichedMessage));
        resetState();
    }


    public void log(int[][][][] message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        String enrichedMessage = EnrichmentUtils.enrichMultiDimensionalArray(message);
        log(new MultiDimArrayMessage(enrichedMessage));
        resetState();
    }


    public  void log(Object... varArgsArray) {
        changeState(LoggerState.NO_BUFFER_STATE);
        if (varArgsArray.length == 0) return;
        String enrichedMessage = EnrichmentUtils.enrichObjectArray(varArgsArray);
        log(new VarArgsMessage(enrichedMessage));
        resetState();
    }

    private void log(LogMessage message) {
        message.enrichWithTypeDescription();
        message.format();
        message.save();
    }


}
