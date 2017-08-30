package com.acme.edu.BusinessProcessors;

import com.acme.edu.Messages.*;

/**
 * Created by vanbkin on 30.08.2017.
 */
public class Decorator implements Visitor {

    @Override
    public void visit(BooleanMessage booleanMessage) {
        booleanMessage.setMessage("primitive: " + booleanMessage.getMessage() );
    }

    @Override
    public void visit(IntMessage intMessage) {
        intMessage.setMessage("primitive: " + intMessage.getMessage() );
    }

    @Override
    public void visit(ArrayMessage arrayMessage) {
        arrayMessage.setMessage("primitives array: " + arrayMessage.getMessage());

    }

    @Override
    public void visit(ByteMessage byteMessage) {
        byteMessage.setMessage("primitive: "+ byteMessage.getMessage() );
    }

    @Override
    public void visit(CharMessage charMessage) {
        charMessage.setMessage("char: " + charMessage.getMessage());
    }

    @Override
    public void visit(MultiDimArrayMessage multiDimArrayMessage) {
        multiDimArrayMessage.setMessage("primitives multimatrix: " + multiDimArrayMessage.getMessage());
    }

    @Override
    public void visit(ObjectMessage objectMessage) {
        objectMessage.setMessage("reference: " + objectMessage.getMessage());
    }

    @Override
    public void visit(StringMessage stringMessage) {
        stringMessage.setMessage("string: " + stringMessage.getMessage());
    }

    @Override
    public void visit(TwoDimArrayMessage twoDimArrayMessage) {
        twoDimArrayMessage.setMessage("primitives matrix: " + twoDimArrayMessage.getMessage());
    }

    @Override
    public void visit(VarArgsMessage varArgsMessage) {
        // NA
    }
}
