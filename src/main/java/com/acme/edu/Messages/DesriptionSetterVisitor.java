package com.acme.edu.Messages;

public class DesriptionSetterVisitor implements Visitor {
    @Override
    public void visit(BooleanMessage booleanMessage) {
        booleanMessage.setTypeDescription("boolean: ");
    }

    @Override
    public void visit(ObjectMessage objectMessage) {
        objectMessage.setTypeDescription("object: ");
    }

    public static void main(String[] args) {

        BooleanMessage booleanMessage = new BooleanMessage("true");
        booleanMessage.accept(new DesriptionSetterVisitor());

        System.out.println(booleanMessage.getTypeDescription());


    }
}
