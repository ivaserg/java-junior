package com.acme.edu;


public class Logger  {


    public static void log(int message) {
        logPrimitive(message);
    }

    public static void log(byte message) {
        logPrimitive(message);
    }

    public static void log(char message) {
        log("char: ", message);
    }

    public static void log(String message) {
        log("string: ", message);
    }

    public static void log(boolean message) {
        logPrimitive(message);
    }

    public static void log(Object message) {
        log("reference: ", message);
    }

    private static void logPrimitive(Object message) {
        log("primitive: ", message);
    }
    
    private static void log(String appendString, Object message) {
        System.out.println(appendString + message);
    }

    public static void main(String[] args) {

    
    }
}
