package com.acme.edu.Messages;

import com.acme.edu.BusinessProcessors.Visitor;
import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

/**
 * Created by vanbkin on 26.08.2017.
 */
public class IntMessage implements Message {
    private String message;
    private Saver saver;
    public boolean isReady = true;

    public IntMessage(String message, Saver saver) {
        this.message=message;
        this.saver=saver;

    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void save() {
        if (isReady) {
            saver.save(message);
        }

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
