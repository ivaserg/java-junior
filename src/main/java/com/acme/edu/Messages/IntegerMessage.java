package com.acme.edu.Messages;

import com.acme.edu.BusinessProcessors.Bufferizator;
import com.acme.edu.BusinessProcessors.Decorator;
import com.acme.edu.BusinessProcessors.LogFormatter;
import com.acme.edu.LoggerState;
import com.acme.edu.Savers.Saver;

/**
 * Created by vanbkin on 30.08.2017.
 */
public class IntegerMessage implements Message {

    private static final String TYPE_DESCRIPTION = "primitive: ";

    private String message;
    private Saver saver;
    private LogFormatter logFormatter;
    private LoggerState loggerState;

    public IntegerMessage(String message, Saver saver, LogFormatter logFormatter, LoggerState loggerState) {
        this.message=message;
        this.saver=saver;
        this.logFormatter=logFormatter;
        this.loggerState=loggerState;
    }


    @Override
    public void save() {
        saver.save(message);
    }

    @Override
    public void visit(Bufferizator bufferizator) {


    }

    @Override
    public void visit(LogFormatter formatter) {

    }

    @Override
    public void visit(Decorator decorator) {
        message = TYPE_DESCRIPTION + message;
    }
}
