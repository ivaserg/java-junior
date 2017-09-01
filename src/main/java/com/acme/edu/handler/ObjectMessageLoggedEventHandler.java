package com.acme.edu.handler;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import com.acme.edu.event.ObjectMessageLoggedEvent;
import com.acme.edu.framework.Handler;

public class ObjectMessageLoggedEventHandler implements Handler<ObjectMessageLoggedEvent> {
    private static final String TYPE_DESCRIPTION = "reference: ";

    private Saver saver;
    private Formatter formatter;

    public ObjectMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }

    @Override
    public void onEvent(ObjectMessageLoggedEvent event) {
        saver.save(formatter.format(TYPE_DESCRIPTION + event.getMessage()));
    }
}
