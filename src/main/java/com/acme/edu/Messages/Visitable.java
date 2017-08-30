package com.acme.edu.Messages;

import com.acme.edu.BusinessProcessors.Decorator;
import com.acme.edu.BusinessProcessors.LogFormatter;
import com.acme.edu.BusinessProcessors.Visitor;

/**
 * Created by vanbkin on 30.08.2017.
 */
public interface Visitable {
     void accept(Visitor visitor);
}
