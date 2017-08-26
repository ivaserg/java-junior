package com.acme.edu.Messages;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

/**
 * Created by vanbkin on 26.08.2017.
 */
public class StringMessage extends LogMessage {
    private static final String TYPE_DESCRIPTION = "string: ";

    public StringMessage(String message, Saver saver, Formatter formatter) {
        super(message, saver, formatter);
    }
    public void addTypeDescription() {
        super.setTypeDescription(TYPE_DESCRIPTION);
        super.addTypeDescription();
    }
}
