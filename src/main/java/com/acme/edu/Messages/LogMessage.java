package com.acme.edu.Messages;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

public abstract class LogMessage {
    private String typeDescription;
    private Saver saver;
    private Formatter formatter;
    private String message;

    public LogMessage(String message, Saver saver, Formatter formatter) {
        this.message = message;
        this.saver = saver;
        this.formatter=formatter;
    }

    public String getMessage() {
        return message;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public void addTypeDescription() {
        this.message = typeDescription + " " + this.message;
    }

    public void save() {
        saver.save(message);
    }

    public void format() {
        message = formatter.format(message);
    }
}
