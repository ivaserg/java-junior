package com.acme.edu;

public class Logger {

    private static LoggerApp loggerApp = new LoggerApp();


    public static void log(int message) {
        loggerApp.log(message);
    }

    public static void log(byte message) {
        loggerApp.log(message);
    }


    public static void log(char message) {
        loggerApp.log(message);
    }


    public static void log(String message) {
        loggerApp.log(message);
    }


    public static void log(boolean message) {
        loggerApp.log(message);
    }


    public static void log(Object message) {
        loggerApp.log(message);
    }


    public static void log(int[] message) {
        loggerApp.log(message);
    }


    public static void log(int[][] message) {
        loggerApp.log(message);
    }


    public static void log(int[][][][] message) {
        loggerApp.log(message);
    }


    public static void log(String... args) {
        loggerApp.log(args);
    }


    public static void log(Integer... args) {
        loggerApp.log(args);
    }

    public static void endLogSession() {
        loggerApp.endLogSession();
    }
}
