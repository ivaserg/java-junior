package com.acme.edu.Messages;

/**
 * Created by vanbkin on 27.08.2017.
 */
public class VarArgsMessage extends LogMessage {
    private static final String TYPE_DESCRIPTION = "";

    public VarArgsMessage(String message) {
        super(message);
        super.setTypeDescription(TYPE_DESCRIPTION);
    }
}
