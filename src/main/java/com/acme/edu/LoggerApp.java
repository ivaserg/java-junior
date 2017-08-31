package com.acme.edu;


import com.acme.edu.Buffers.LogBuffer;
import com.acme.edu.Buffers.AggregationLogBufferFlusher;
import com.acme.edu.Buffers.LogBufferInputState;
import com.acme.edu.Formatters.DefaultFormatter;
import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Messages.*;
import com.acme.edu.Savers.ConsoleSaver;
import com.acme.edu.Savers.Saver;
import com.acme.edu.event.*;
import com.acme.edu.framework.EventDispatcher;
import com.acme.edu.handler.*;

public class LoggerApp {
    private LogBuffer logBuffer;
    private LoggerState loggerState = new LoggerState();
    EventDispatcher dispatcher;

    {
        dispatcher = new EventDispatcher();
        Saver saver = new ConsoleSaver();
        Formatter formatter = new DefaultFormatter();
        dispatcher.registerHandler(BooleanMessageLoggedEvent.class, new BooleanMessageLoggedEventHandler(saver,formatter));
        dispatcher.registerHandler(CharMessageLoggedEvent.class, new CharMessageLoggedEventHandler(saver,formatter));
        dispatcher.registerHandler(ObjectMessageLoggedEvent.class, new ObjectMessageLoggedEventHandler(saver,formatter));
        dispatcher.registerHandler(IntMessageLoggedEvent.class, new IntMessageLoggedEventHandler(saver,formatter));
        dispatcher.registerHandler(ByteMessageLoggedEvent.class, new ByteMessageLoggedEventHandler(saver,formatter));
        dispatcher.registerHandler(StringMessageLoggedEvent.class, new StringMessageLoggedEventHandler(saver,formatter));

        dispatcher.registerHandler(FlushCacheEvent.class, new FlushCacheEventHandler(saver,formatter,dispatcher));

    }


    public LoggerApp() {
        LogMessage.saver = new ConsoleSaver();
        logBuffer = new LogBuffer(new AggregationLogBufferFlusher());
    }

    public LoggerApp(Saver saver, Formatter formatter, LogBuffer logBuffer) {
        LogMessage.saver = saver;
        this.logBuffer = logBuffer;
    }


    public void endLogSession() {
        loggerState.setCurrentState(loggerState.getPreviousState());
        flushCache();
    }

    public void flushCache() {
        dispatcher.dispatch(new FlushCacheEvent(loggerState.getPreviousState()));
    }



    public void log(int message) {
        loggerState.setCurrentState(State.INT_INPUT);
        if (loggerState.isStateSwitched()) flushCache();
        dispatcher.dispatch(new IntMessageLoggedEvent(String.valueOf(message), true));
    }


    public void log(byte message) {
        loggerState.setCurrentState(State.BYTE_INPUT);
        if (loggerState.isStateSwitched()) flushCache();
        dispatcher.dispatch(new ByteMessageLoggedEvent(String.valueOf(message), true));
    }

    public void log(char message) {
        loggerState.setCurrentState(State.CHAR_INPUT);
        if (loggerState.isStateSwitched()) flushCache();
        dispatcher.dispatch(new CharMessageLoggedEvent(String.valueOf(message)));
    }

    public void log(String message) {
        if (message == null) return;
        loggerState.setCurrentState(State.STRING_INPUT);
        if (loggerState.isStateSwitched()) flushCache();
        dispatcher.dispatch(new StringMessageLoggedEvent(message, true));
    }

    public void log(boolean message) {
        loggerState.setCurrentState(State.BOOLEAN_INPUT);
        if (loggerState.isStateSwitched()) flushCache();
        dispatcher.dispatch(new BooleanMessageLoggedEvent(Boolean.toString(message)));

    }

    public void log(Object message) {
        if (message == null) return;
        loggerState.setCurrentState(State.OBJECT_INPUT);
        if (loggerState.isStateSwitched()) flushCache();
        dispatcher.dispatch(new ObjectMessageLoggedEvent(message.toString()));
    }

//    public void log(int[] message) {
//        if (message == null) return;
//        logBuffer.setLogBufferInputState(LogBufferInputState.ARRAY_INPUT);
//        String enrichedMessage = ArraysEnrichmentUtils.enrichOneDimensionalArray(message);
//        logBuffer.addMessage(new ArrayMessage(enrichedMessage));
//    }
//
//
//    public void log(int[][] message) {
//        if (message == null) return;
//        logBuffer.setLogBufferInputState(LogBufferInputState.TWODIM_ARRAY_INPUT);
//        String enrichedMessage = ArraysEnrichmentUtils.enrichTwoDimensionalArray(message);
//        logBuffer.addMessage(new TwoDimArrayMessage(enrichedMessage));
//    }
//
//
//    public void log(int[][][][] message) {
//        if (message == null) return;
//        logBuffer.setLogBufferInputState(LogBufferInputState.MULTIDIM_ARRAY_INPUT);
//        String enrichedMessage = ArraysEnrichmentUtils.enrichMultiDimensionalArray(message);
//        logBuffer.addMessage(new MultiDimArrayMessage(enrichedMessage));
//    }
//
//
//    public void log(Object... varArgsArray) {
//        if (varArgsArray.length == 0) return;
//        logBuffer.setLogBufferInputState(LogBufferInputState.VARARGS_ARRAY_INPUT);
//        String enrichedMessage = ArraysEnrichmentUtils.enrichObjectArray(varArgsArray);
//        logBuffer.addMessage(new VarArgsMessage(enrichedMessage));
//    }

    public static void main(String[] args) {

        LoggerApp loggerApp = new LoggerApp();
        loggerApp.log("str 3");
        loggerApp.log("str 3");
        loggerApp.endLogSession();


    }
}
