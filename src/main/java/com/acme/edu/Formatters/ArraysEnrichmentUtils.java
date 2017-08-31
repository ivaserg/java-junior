package com.acme.edu.Formatters;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

/**
 * Created by vanbkin on 27.08.2017.
 */
public abstract class ArraysEnrichmentUtils {

    public static String enrichObjectArray(Object[] array) {
        StringBuilder sb  = new StringBuilder();
        for (Object o : array) {
            sb.append(o.toString()).append(lineSeparator());
        }
        return sb.toString();

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
                    sb.append(ArraysEnrichmentUtils.arrayToString(k)).append(lineSeparator());
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
