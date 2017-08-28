package com.acme.edu;


import com.acme.edu.Formatters.DefaultFormatter;
import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Messages.*;
import com.acme.edu.Savers.ConsoleSaver;
import com.acme.edu.Savers.Saver;

public class LoggerApp {
    private LoggerState currentLoggerState = LoggerState.NO_BUFFER_STATE;
    private LogBuffer logBuffer = new LogBuffer();


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
            logBuffer.flushMessagesDependingOnState(currentLoggerState);
            resetState();
        }
//        logBuffer.flushMessagesDependingOnState(currentLoggerState);
        currentLoggerState = newLoggerState;
    }



    public void endLogSession() {
        logBuffer.flushMessagesDependingOnState(currentLoggerState);
        resetState();
    }



    public void log(int message) {
        changeState(LoggerState.INT_BUFFER_STATE);
        logBuffer.addMessage(new IntMessage(Integer.toString(message)));
    }


    public void log(byte message) {
        changeState(LoggerState.BYTE_BUFFER_STATE);
        logBuffer.addMessage(new ByteMessage(Byte.toString(message)));
    }

    public void log(char message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        logBuffer.addMessage(new CharMessage(Character.toString(message)));
        resetState();
    }

    public void log(String message) {
        if (message == null) return;
        changeState(LoggerState.STRING_BUFFER_STATE);
        logBuffer.addMessage(new StringMessage(message));
    }


    public void log(boolean message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        logBuffer.addMessage(new BooleanMessage(Boolean.toString(message)));
        resetState();
    }

    public void log(Object message) {
        if (message == null) return;
        changeState(LoggerState.NO_BUFFER_STATE);
        logBuffer.addMessage(new ObjectMessage(message.toString()));
        resetState();
    }

    public void log(int[] message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        String enrichedMessage = EnrichmentUtils.enrichOneDimensionalArray(message);
        logBuffer.addMessage(new ArrayMessage(enrichedMessage));
        resetState();
    }


    public void log(int[][] message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        String enrichedMessage = EnrichmentUtils.enrichTwoDimensionalArray(message);
        logBuffer.addMessage(new TwoDimArrayMessage(enrichedMessage));
        resetState();
    }


    public void log(int[][][][] message) {
        changeState(LoggerState.NO_BUFFER_STATE);
        String enrichedMessage = EnrichmentUtils.enrichMultiDimensionalArray(message);
        logBuffer.addMessage(new MultiDimArrayMessage(enrichedMessage));
        resetState();
    }


    public  void log(Object... varArgsArray) {
        changeState(LoggerState.NO_BUFFER_STATE);
        if (varArgsArray.length == 0) return;
        String enrichedMessage = EnrichmentUtils.enrichObjectArray(varArgsArray);
        logBuffer.addMessage(new VarArgsMessage(enrichedMessage));
        resetState();
    }



    public static void main(String[] args) {

        System.out.println(Byte.MIN_VALUE);
        Logger.log("str 1");
        Logger.log((byte)-10);
        Logger.log((byte)Byte.MIN_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.endLogSession();

        System.out.println(Byte.MIN_VALUE - 10);
    }


}
