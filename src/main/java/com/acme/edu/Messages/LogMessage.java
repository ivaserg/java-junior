package com.acme.edu.Messages;

import com.acme.edu.Formatters.DescriptionFormatter;
import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

import java.util.ArrayList;
import java.util.List;

public abstract class LogMessage {
    public static Saver saver;

    private List<Formatter> formattersList = new ArrayList<>();
    private String typeDescription;
    private String message;

    public LogMessage(String message) {
        this.message = message;
        addFormatter(new DescriptionFormatter());
    }

    private void addFormatter(Formatter formatter) {
        formattersList.add(formatter);
    }

    public void setMessage(String message) {
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

    public void format() {
        for (Formatter formatter: formattersList) {
            formatter.format(this);
        }
    }

    public void save() {
        saver.save(message);
    }




}
