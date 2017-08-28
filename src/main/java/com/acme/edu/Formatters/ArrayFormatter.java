package com.acme.edu.Formatters;

import com.acme.edu.Messages.EnrichmentUtils;
import com.acme.edu.Messages.LogMessage;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class ArrayFormatter implements Formatter {
    @Override
    public void format(LogMessage logMessage) {

    }

    public String formatObjectArray(Object[] array) {
        StringBuilder sb  = new StringBuilder();
        for (Object o : array) {
            sb.append(o.toString()).append(lineSeparator());
        }
        return sb.toString();
    }

    public String formatOneDimensionalArray(int[] array) {

        return arrayToString(array);
    }

    public String formatTwoDimensionalArray(int[][] array) {
        StringBuilder sb = new StringBuilder("{" + lineSeparator());
        for (int[] i : array) {
            sb.append(arrayToString(i)).append(lineSeparator());
        }
        sb.append('}');
        return sb.toString();
    }

    public String formatMultiDimensionalArray(int[][][][] array) {
        StringBuilder sb = new StringBuilder("{" + lineSeparator());

        for (int[][][] i : array) {
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

        return sb.toString();
    }

    private static String arrayToString(int[] array) {
        return Arrays.stream(Arrays.stream(array)
                .mapToObj(String::valueOf)
                .toArray(String[]::new))
                .collect(Collectors.joining(", ", "{", "}"));

    }


}
