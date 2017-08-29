package com.acme.edu.Messages;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

/**
 * Created by vanbkin on 26.08.2017.
 */
public class IntMessage extends LogMessage {
    private static final String TYPE_DESCRIPTION = "primitive: ";

    public IntMessage(String message) {
        super(message);
        super.setTypeDescription(TYPE_DESCRIPTION);
    }

}
