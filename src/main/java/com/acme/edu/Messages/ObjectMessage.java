package com.acme.edu.Messages;

/**
 * Created by vanbkin on 26.08.2017.
 */
public class ObjectMessage extends LogMessage implements Visitable{
    private static final String TYPE_DESCRIPTION = "reference: ";

    public ObjectMessage(String message) {
        super(message);
        super.setTypeDescription(TYPE_DESCRIPTION);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
