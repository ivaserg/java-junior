package com.acme.edu.saver;

import com.acme.edu.exception.MessageSavingException;

public interface Saver {

    void save(String message) throws MessageSavingException;
}
