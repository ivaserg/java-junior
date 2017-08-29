package com.acme.edu.Messages;

public interface Visitor {

    void visit(BooleanMessage booleanMessage);
    void visit(ObjectMessage objectMessage);
}
