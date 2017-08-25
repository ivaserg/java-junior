package com.acme.edu;


import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class Logger {
    private static int sumOfIntegers;
    private static byte sumOfBytes;
    private static String currentString = "";
    private static int counter;
    private static State currentState = State.INIT_STATE;
    private static String buffer = "";


    private static void resetState() {
        sumOfIntegers = 0;
        sumOfBytes = 0;
        currentString = "";
        counter = 0;
        buffer = "";
        currentState = State.INIT_STATE;
    }


    private static void changeState(State newState) {
        if (currentState != State.INIT_STATE && currentState != newState) {
            flush();
            resetState();
        }
        currentState = newState;
    }

    private static void flush() {
        printOut(currentState.getRelevantTypeDescription() + buffer);
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

    public static void endLogSession() {
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

    public static void log(boolean message) {
        changeState(State.BOOLEAN_STATE);
        buffer = Boolean.toString(message);
        flush();
        resetState();
    }

    public static void log(Object message) {
        if (message == null) return;
        changeState(State.OBJECT_STATE);
        buffer = message.toString();
        flush();
        resetState();
    }


    public static void log(int[] message) {
        changeState(State.PRIMITIVE_ARRAY_STATE);
        buffer = arrayToString(message);
        flush();
        resetState();
    }

    public static void log(int[][] message) {
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

    public static void log(int[][][][] message) {
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

    public static void log(String... args) {
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

    public static void log(Integer... args) {
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


    private static void printOut(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) {

    }
}
