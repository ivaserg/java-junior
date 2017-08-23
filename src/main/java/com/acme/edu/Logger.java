package com.acme.edu;


public class Logger {

    private static Logger singletonLogger;

    private Logger() {
    }

    public Logger createInstance() {
        if (singletonLogger == null ) {
            synchronized (Logger.class) {
             if (singletonLogger == null) {
                 singletonLogger = new Logger();
             }
            }
        }
        return singletonLogger;
    }



    public static void log(int message) {
        System.out.println("primitive: " + message);
    }

    public static void log(byte message) {
        System.out.println("primitive: " + message);
    }

    public static void log(char message) {
        System.out.println("char: " + message);
    }

    public static void log(String message) {
        System.out.println("string: " + message);
    }

    public static void log(boolean message) {
        System.out.println("primitive: " + message);
    }

    public static void log(Object message) {
        System.out.println("reference: " + message);
    }

    public static void main(String[] args) {


    }
}
