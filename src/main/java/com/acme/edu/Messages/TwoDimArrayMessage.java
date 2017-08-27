package com.acme.edu.Messages;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

/**
 * Created by vanbkin on 27.08.2017.
 */
public class TwoDimArrayMessage extends LogMessage {
    private static final String TYPE_DESCRIPTION = "primitives matrix: ";

    public TwoDimArrayMessage(String message, Saver saver, Formatter formatter) {
        super(message, saver, formatter);
        super.setTypeDescription(TYPE_DESCRIPTION);
    }
}
