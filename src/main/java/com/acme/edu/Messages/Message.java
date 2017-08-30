package com.acme.edu.Messages;

import com.acme.edu.BusinessProcessors.Visitor;

/**
 * Created by vanbkin on 30.08.2017.
 */
public interface Message extends Visitable {
    String getMessage();
    void setMessage(String message);
    void save();
}
