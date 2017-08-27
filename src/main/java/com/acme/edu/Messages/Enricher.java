package com.acme.edu.Messages;

import com.acme.edu.LoggerApp;
import com.acme.edu.Messages.LogMessage;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

/**
 * Created by vanbkin on 27.08.2017.
 */
public class Enricher {

    public static void enrichWithDescription(LogMessage message) {
         message.setMessage(message.getTypeDescription() + message.getMessage());
    }

    public static String enrichOneDimensionalArray(int[] array) {
        return arrayToString(array);

    }

    public static String enrichTwoDimensionalArray(int[][] array) {
        StringBuilder sb = new StringBuilder("{" + lineSeparator());
        for (int[] i : array) {
            sb.append(arrayToString(i)).append(lineSeparator());
        }
        sb.append('}');
        return sb.toString();
    }

    public static String enrichMultiDimensionalArray(int[][][][] array) {
        StringBuilder sb = new StringBuilder("{" + lineSeparator());

        for (int[][][] i : array) {
            sb.append("{" + lineSeparator());
            for (int[][] j : i) {
                sb.append("{" + lineSeparator());
                for (int[] k : j) {
                    sb.append(Enricher.arrayToString(k)).append(lineSeparator());
                }
                sb.append("}" + lineSeparator());
            }
            sb.append("}" + lineSeparator());
        }
        sb.append('}');

        return sb.toString();
    }

    public static String arrayToString(int[] array) {
        return Arrays.stream(Arrays.stream(array)
                .mapToObj(String::valueOf)
                .toArray(String[]::new))
                .collect(Collectors.joining(", ", "{", "}"));

    }

//    private static String[] getStringArray(int[] array) {
//        return Arrays.stream(array)
//                .mapToObj(String::valueOf)
//                .toArray(String[]::new);
//    }
}
