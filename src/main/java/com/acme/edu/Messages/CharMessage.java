package com.acme.edu.Messages;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

/**
 * Created by vanbkin on 26.08.2017.
 */
public class CharMessage extends LogMessage {
    private static final String TYPE_DESCRIPTION = "char: ";

    public CharMessage(String message) {

        super(message);
        super.setTypeDescription(TYPE_DESCRIPTION);
    }

}
