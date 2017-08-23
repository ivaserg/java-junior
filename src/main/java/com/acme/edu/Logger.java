package com.acme.edu;


public class Logger  {


    public static void log(int message) {
        log("primitive: ", message);
    }

    public static void log(byte message) {
        log("primitive: ", message);
    }

    public static void log(char message) {
        log("char: ", message);
    }

    public static void log(String message) {
        log("string: ", message);
    }

    public static void log(boolean message) {
        log("primitive: ", message);
    }

    public static void log(Object message) {
        log("reference: ", message);
    }

    private static void log(String appendString, Object message) {
        System.out.println(appendString + message);
    }

    public static void main(String[] args) {

    }
}
