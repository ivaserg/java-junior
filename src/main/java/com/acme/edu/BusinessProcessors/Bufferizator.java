package com.acme.edu.BusinessProcessors;

import com.acme.edu.Messages.Visitor;

/**
 * Created by vanbkin on 30.08.2017.
 */
public class Bufferizator implements Visitable {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
