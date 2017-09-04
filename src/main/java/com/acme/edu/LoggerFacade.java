package com.acme.edu;

import com.acme.edu.formatter.DefaultFormatter;
import com.acme.edu.saver.ConsoleSaver;

public class LoggerFacade {

    private static ConsoleMessageEventHandler consoleEvenetHandler = new ConsoleMessageEventHandler(new ConsoleSaver(), new DefaultFormatter());


    public static void log(int message) {
        consoleEvenetHandler.log(message);
    }

    public static void log(byte message) {
        consoleEvenetHandler.log(message);
    }


    public static void log(char message) {
        consoleEvenetHandler.log(message);
    }


    public static void log(String message) {
        consoleEvenetHandler.log(message);
    }


    public static void log(boolean message) {
        consoleEvenetHandler.log(message);
    }


    public static void log(Object message) {
        consoleEvenetHandler.log(message);
    }


    public static void log(int[] message) {
        consoleEvenetHandler.log(message);
    }


    public static void log(int[][] message) {
        consoleEvenetHandler.log(message);
    }


    public static void log(int[][][][] message) {
        consoleEvenetHandler.log(message);
    }


    public static void log(String... args) {
        consoleEvenetHandler.log(args);
    }


    public static void log(Integer... args) {
        consoleEvenetHandler.log(args);
    }

    public static void endLogSession() {
        consoleEvenetHandler.endLogSession();
    }

}
