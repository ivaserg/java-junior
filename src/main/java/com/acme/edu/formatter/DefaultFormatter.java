package com.acme.edu.formatter;

/**
 * Created by vanbkin on 26.08.2017.
 */
public class DefaultFormatter implements Formatter {

    @Override
    public String format(String message) {
        return message;
    }
}
