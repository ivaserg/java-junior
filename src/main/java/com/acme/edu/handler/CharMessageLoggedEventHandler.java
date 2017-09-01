package com.acme.edu.handler;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;
import com.acme.edu.event.CharMessageLoggedEvent;
import com.acme.edu.framework.Handler;

public class CharMessageLoggedEventHandler implements Handler<CharMessageLoggedEvent> {
    private static final String TYPE_DESCRIPTION = "char: ";

    private Saver saver;
    private Formatter formatter;

    public CharMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }

    @Override
    public void onEvent(CharMessageLoggedEvent event) {
        saver.save(formatter.format(TYPE_DESCRIPTION + event.getMessage()));
    }
}
