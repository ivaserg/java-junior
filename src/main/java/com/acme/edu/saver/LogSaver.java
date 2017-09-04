package com.acme.edu.saver;

import com.acme.edu.exception.LogSaverException;

import java.io.*;

public class LogSaver implements Saver {
    @Override
    public void save(String message) throws LogSaverException {

        File file = new File("/log.txt");

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(message);
            fileWriter.flush();
        } catch (IOException e) {
            throw new LogSaverException(e.getMessage());
        }

    }
}
