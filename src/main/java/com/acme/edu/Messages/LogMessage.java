package com.acme.edu.Messages;

import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

public abstract class LogMessage {
    public static Formatter formatter;
    public static Saver saver;

    private String typeDescription;
    private String message;



    public LogMessage(String message) {
        this.message = message;
    }

    void setMessage(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void enrichWithTypeDescription() {
        setMessage(typeDescription + message);
    }

    public void format() {
        formatter.format(message);
    }

    public void save() {
        saver.save(message);
    }


}
