package com.acme.edu;


import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum State
{
    NO_STATE("primitive: "),
    INT_STATE("primitive: "),
    STRING_STATE("string: "),
    BYTE_STATE("primitive: "),
    CHAR_STATE("char: "),
    BOOLEAN_STATE("primitive: "),
    PRIMITIVE_ARRAY_STATE("primitive array: "),
    OBJECT_STATE("reference: ");

    private final String relevantTypeDescription;

    String getRelevantTypeDescription() {
        return relevantTypeDescription;
    }

     State (String relevantTypeDescription) {
        this.relevantTypeDescription = relevantTypeDescription;
    }
}

public class Logger {
    private static int sumOfIntegers;
    private static byte sumOfBytes;
    private static String currentString = "";
    private static int counter;
    private static State state = State.NO_STATE;
    private static String buffer = "";

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                endLogSession();
            }
        }));
    }

    private static void resetState() {
        sumOfIntegers = 0;
        sumOfBytes = 0;
        currentString = "";
        counter = 0;
        buffer = "";
        state = State.NO_STATE;
    }


    private static void changeState(State newState) {
        if (state != State.NO_STATE && state != newState) {
            flush();
            resetState();
        }
        state = newState;
    }

    private static void flush() {
        printOut(state.getRelevantTypeDescription() + buffer);
        buffer = "";
    }

    public static void log(int message) {
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

    public static void endLogSession () {
        flush();
        resetState();
    }

    public static void log(byte message) {
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

    public static void log(char message) {
        changeState(State.CHAR_STATE);
        buffer = Character.toString(message);
        flush();
        resetState();
    }

    public static void log(String message) {
        changeState(State.STRING_STATE);
        if (!currentString.isEmpty() && !currentString.equals(message)) {
            flush();
            counter = 0;
        }
        counter++;
        currentString = message;
        String numStr = counter > 1 ? " (x" + counter + ")" : "";
        buffer = currentString + numStr;
    }

    public static void log(boolean message) {
        changeState(State.BOOLEAN_STATE);
        buffer = Boolean.toString(message);
        flush();
        resetState();
    }

    public static void log(Object message) {
        changeState(State.OBJECT_STATE);
        buffer = message.toString();
        flush();
        resetState();
    }

    public static void log(int[] message) {
        changeState(State.PRIMITIVE_ARRAY_STATE);
        buffer = arrarToString(message);
        flush();
        resetState();

    }

    public static String arrarToString(int[] array) {
        return "";
    }



    private static void printOut(String input){
        System.out.println(input);
    }

    public static void main(String[] args) {

        Logger.log(new int[] {-1, 0, 1});



    }

}
