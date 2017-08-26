package com.acme.edu;


import com.acme.edu.Formatters.DefaultFormatter;
import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Messages.BooleanMessage;
import com.acme.edu.Messages.CharMessage;
import com.acme.edu.Messages.LogMessage;
import com.acme.edu.Messages.ObjectMessage;
import com.acme.edu.Savers.ConsoleSaver;
import com.acme.edu.Savers.Saver;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class LoggerApp {
    private Saver saver;
    private Formatter formatter;

    private int sumOfIntegers;
    private byte sumOfBytes;
    private  String currentString = "";
    private  int counter;
    private  State currentState = State.INIT_STATE;
    private  String buffer = "";


    public LoggerApp() {
        this.saver=new ConsoleSaver();
        this.formatter=new DefaultFormatter();
    }

    public LoggerApp(Saver saver, Formatter formatter) {
        this.saver=saver;
        this.formatter=formatter;
    }

    private  void resetState() {
        sumOfIntegers = 0;
        sumOfBytes = 0;
        currentString = "";
        counter = 0;
        buffer = "";
        currentState = State.INIT_STATE;
    }


    private  void changeState(State newState) {
        if (currentState != State.INIT_STATE && currentState != newState) {
            flush();
            resetState();
        }
        currentState = newState;
    }

    private  void flush() {
        printOut(currentState.getRelevantTypeDescription() + buffer);
        buffer = "";
    }

    public  void log(int message) {
        changeState(State.INT_STATE);
        boolean isOverflow = Integer.MAX_VALUE - sumOfIntegers < message;
        if (isOverflow) {
            flush();
            sumOfIntegers = Integer.MAX_VALUE;
        } else {
            sumOfIntegers += message;
        }
        buffer = Integer.toString(sumOfIntegers);
    }

    public  void endLogSession() {
        flush();
        resetState();
    }


    public  void log(byte message) {
        changeState(State.BYTE_STATE);
        boolean overflow = Byte.MAX_VALUE - sumOfBytes < message;
        if (overflow) {
            flush();
            sumOfBytes = Byte.MAX_VALUE;
        } else {
            sumOfBytes += message;
        }
        buffer = Byte.toString(sumOfBytes);
    }

    public  void log(char message) {
        changeState(State.CHAR_STATE);
        LogMessage charMessage = new CharMessage(Character.toString(message), saver, formatter);
        log(charMessage);
        resetState();
    }

    public  void log(String message) {
        if (message == null) return;
        changeState(State.STRING_STATE);
        if (!currentString.isEmpty() && !currentString.equals(message)) {
            flush();
            counter = 0;
        }
        counter++;
        currentString = message;
        String numberOfStrings = counter > 1 ? " (x" + counter + ")" : "";
        buffer = currentString + numberOfStrings;
    }


    public  void log(boolean message) {
        changeState(State.BOOLEAN_STATE);
        LogMessage booleanMessage = new BooleanMessage(Boolean.toString(message), saver, formatter);
        log(booleanMessage);
        resetState();
    }

    public  void log(Object message) {
        if (message == null) return;
        changeState(State.OBJECT_STATE);
        ObjectMessage objectMessage = new ObjectMessage(message.toString(), saver, formatter);
        log(objectMessage);
        resetState();
    }

    public  void log(int[] message) {
        changeState(State.PRIMITIVE_ARRAY_STATE);
        buffer = arrayToString(message);
        flush();
        resetState();
    }


    public  void log(int[][] message) {
        changeState(State.PRIMITIVES_MATRIX_ARRAY_STATE);
        StringBuilder sb = new StringBuilder("{" + lineSeparator());
        for (int[] i : message) {
            sb.append(arrayToString(i)).append(lineSeparator());
        }
        sb.append('}');
        buffer = sb.toString();
        flush();
        resetState();
    }


    public  void log(int[][][][] message) {
        changeState(State.PRIMITIVES_MULTIMATRIX_ARRAY_STATE);
        StringBuilder sb = new StringBuilder("{" + lineSeparator());

        for (int[][][] i : message) {
            sb.append("{" + lineSeparator());
            for (int[][] j : i) {
                sb.append("{" + lineSeparator());
                for (int[] k : j) {
                    sb.append(arrayToString(k)).append(lineSeparator());
                }
                sb.append("}" + lineSeparator());
            }
            sb.append("}" + lineSeparator());
        }

        sb.append('}');
        buffer = sb.toString();
        flush();
        resetState();
    }


    public  void log(String... args) {
        changeState(State.VARARGS_STATE);
        if (args.length == 0) return;
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s).append(lineSeparator());
        }
        buffer = sb.toString();
        flush();
        resetState();

    }


    public  void log(Integer... args) {
        changeState(State.VARARGS_STATE);
        if (args.length == 0) return;
        StringBuilder sb = new StringBuilder();
        for (Integer i : args) {
            sb.append(i).append(lineSeparator());
        }
        buffer = sb.toString();
        flush();
        resetState();

    }

    private static String arrayToString(int[] array) {
        return Arrays.stream(getStringArray(array))
                .collect(Collectors.joining(", ", "{", "}"));

    }

    private static String[] getStringArray(int[] array) {
        return Arrays.stream(array)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
    }

    private void log(LogMessage message) {
        message.enrichWithTypeDescription();
        message.format();
        message.save();
    }


    private void printOut(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) {
        LoggerApp loggerApp = new LoggerApp();
        ObjectMessage logMessage = new ObjectMessage("nww", loggerApp.saver, loggerApp.formatter);
        logMessage.enrichWithTypeDescription();
        System.out.println(logMessage.getMessage());



    }

}
