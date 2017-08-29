package com.acme.edu.Messages;

import com.acme.edu.BusinessProcessors.Bufferizator;
import com.acme.edu.BusinessProcessors.Decorator;
import com.acme.edu.BusinessProcessors.LogFormatter;
import com.acme.edu.Formatters.Formatter;
import com.acme.edu.Savers.Saver;

/**
 * Created by vanbkin on 30.08.2017.
 */
public interface Visitor {
    void visit(Bufferizator bufferizator);
    void visit(LogFormatter formatter);
    void visit(Decorator decorator);
}
