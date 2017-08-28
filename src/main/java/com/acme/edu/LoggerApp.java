package com.acme.edu;


import com.acme.edu.Buffers.AggregationLogBuffer;
import com.acme.edu.Buffers.LogBuffer;
import com.acme.edu.Formatters.DescriptionFormatter;
import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Messages.*;
import com.acme.edu.Savers.ConsoleSaver;
import com.acme.edu.Savers.Saver;

public class LoggerApp {
    private LogBuffer logBuffer;


    public LoggerApp() {
        LogMessage.saver = new ConsoleSaver();
        LogMessage.formatter = new DescriptionFormatter();
        logBuffer = new AggregationLogBuffer();
    }

    public LoggerApp(Saver saver, Formatter formatter, LogBuffer logBuffer) {
        LogMessage.saver = saver;
        LogMessage.formatter = formatter;
        this.logBuffer = logBuffer;
    }


    public void endLogSession() {
        logBuffer.flushBuffer();
    }

    public void log(int message) {
        logBuffer.addMessage(new IntMessage(Integer.toString(message)));
    }


    public void log(byte message) {
        logBuffer.addMessage(new ByteMessage(Byte.toString(message)));
    }

    public void log(char message) {
        logBuffer.addMessage(new CharMessage(Character.toString(message)));
    }

    public void log(String message) {
        if (message == null) return;
        logBuffer.addMessage(new StringMessage(message));
    }

    public void log(boolean message) {
        logBuffer.addMessage(new BooleanMessage(Boolean.toString(message)));
    }

    public void log(Object message) {
        if (message == null) return;
        logBuffer.addMessage(new ObjectMessage(message.toString()));
    }

    public void log(int[] message) {
        String enrichedMessage = EnrichmentUtils.enrichOneDimensionalArray(message);
        logBuffer.addMessage(new ArrayMessage(enrichedMessage));
    }


    public void log(int[][] message) {
        String enrichedMessage = EnrichmentUtils.enrichTwoDimensionalArray(message);
        logBuffer.addMessage(new TwoDimArrayMessage(enrichedMessage));
    }


    public void log(int[][][][] message) {
        String enrichedMessage = EnrichmentUtils.enrichMultiDimensionalArray(message);
        logBuffer.addMessage(new MultiDimArrayMessage(enrichedMessage));
    }


    public void log(Object... varArgsArray) {
        if (varArgsArray.length == 0) return;
        String enrichedMessage = EnrichmentUtils.enrichObjectArray(varArgsArray);
        logBuffer.addMessage(new VarArgsMessage(enrichedMessage));
    }

}
