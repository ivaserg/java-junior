package com.acme.edu.Messages;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

/**
 * Created by vanbkin on 27.08.2017.
 */
public class ArrayMessage extends LogMessage {
    private static final String TYPE_DESCRIPTION = "primitives array: ";

    public ArrayMessage(String message) {
        super(message);
        super.setTypeDescription(TYPE_DESCRIPTION);
    }
}
