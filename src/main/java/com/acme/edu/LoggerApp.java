package com.acme.edu;


import com.acme.edu.Buffers.LogBuffer;
import com.acme.edu.Buffers.AggregationLogBufferFlusher;
import com.acme.edu.Buffers.LogBufferInputState;
import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Messages.*;
import com.acme.edu.Savers.ConsoleSaver;
import com.acme.edu.Savers.Saver;

public class LoggerApp {
    private LogBuffer logBuffer;

    public LoggerApp() {
        LogMessage.saver = new ConsoleSaver();
        logBuffer = new LogBuffer(new AggregationLogBufferFlusher());
    }

    public LoggerApp(Saver saver, Formatter formatter, LogBuffer logBuffer) {
        LogMessage.saver = saver;
        this.logBuffer = logBuffer;
    }


    public void endLogSession() {
        logBuffer.flushBuffer();
    }

    public void log(int message) {
        logBuffer.setLogBufferInputState(LogBufferInputState.INT_INPUT);
        logBuffer.addMessage(new IntMessage(Integer.toString(message)));
    }


    public void log(byte message) {
        logBuffer.setLogBufferInputState(LogBufferInputState.BYTE_INPUT);
        logBuffer.addMessage(new ByteMessage(Byte.toString(message)));
    }

    public void log(char message) {
        logBuffer.setLogBufferInputState(LogBufferInputState.CHAR_INPUT);
        logBuffer.addMessage(new CharMessage(Character.toString(message)));
    }

    public void log(String message) {
        if (message == null) return;
        logBuffer.setLogBufferInputState(LogBufferInputState.STRING_INPUT);
        logBuffer.addMessage(new StringMessage(message));
    }

    public void log(boolean message) {
        logBuffer.setLogBufferInputState(LogBufferInputState.BOOLEAN_INPUT);
        logBuffer.addMessage(new BooleanMessage(Boolean.toString(message)));
    }

    public void log(Object message) {
        if (message == null) return;
        logBuffer.setLogBufferInputState(LogBufferInputState.OBJECT_INPUT);
        logBuffer.addMessage(new ObjectMessage(message.toString()));
    }

    public void log(int[] message) {
        if (message == null) return;
        logBuffer.setLogBufferInputState(LogBufferInputState.ARRAY_INPUT);
        String enrichedMessage = ArraysEnrichmentUtils.enrichOneDimensionalArray(message);
        logBuffer.addMessage(new ArrayMessage(enrichedMessage));
    }


    public void log(int[][] message) {
        if (message == null) return;
        logBuffer.setLogBufferInputState(LogBufferInputState.TWODIM_ARRAY_INPUT);
        String enrichedMessage = ArraysEnrichmentUtils.enrichTwoDimensionalArray(message);
        logBuffer.addMessage(new TwoDimArrayMessage(enrichedMessage));
    }


    public void log(int[][][][] message) {
        if (message == null) return;
        logBuffer.setLogBufferInputState(LogBufferInputState.MULTIDIM_ARRAY_INPUT);
        String enrichedMessage = ArraysEnrichmentUtils.enrichMultiDimensionalArray(message);
        logBuffer.addMessage(new MultiDimArrayMessage(enrichedMessage));
    }


    public void log(Object... varArgsArray) {
        if (varArgsArray.length == 0) return;
        logBuffer.setLogBufferInputState(LogBufferInputState.VARARGS_ARRAY_INPUT);
        String enrichedMessage = ArraysEnrichmentUtils.enrichObjectArray(varArgsArray);
        logBuffer.addMessage(new VarArgsMessage(enrichedMessage));
    }

    public static void main(String[] args) {
        LogBuffer sut = new LogBuffer(new AggregationLogBufferFlusher());

        sut.addMessage(new IntMessage("9"));


    }
}
