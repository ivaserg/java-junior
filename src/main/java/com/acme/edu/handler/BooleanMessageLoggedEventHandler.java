package com.acme.edu.handler;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import com.acme.edu.event.BooleanMessageLoggedEvent;
import com.acme.edu.framework.Handler;

public class BooleanMessageLoggedEventHandler implements Handler<BooleanMessageLoggedEvent> {
    private static final String TYPE_DESCRIPTION = "primitive: ";

    private Saver saver;
    private Formatter formatter;

    public BooleanMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }

    @Override
    public void onEvent(BooleanMessageLoggedEvent event) {
        saver.save(formatter.format(TYPE_DESCRIPTION + event.getMessage()));
    }
}
