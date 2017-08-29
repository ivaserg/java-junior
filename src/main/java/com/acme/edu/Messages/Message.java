package com.acme.edu.Messages;

import com.acme.edu.BusinessProcessors.Visitable;
import com.acme.edu.Savers.Saver;

/**
 * Created by vanbkin on 30.08.2017.
 */
public interface Message extends Visitor {
    void save();
}
