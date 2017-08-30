package com.acme.edu.BusinessProcessors;

import com.acme.edu.Messages.*;

/**
 * Created by vanbkin on 30.08.2017.
 */
public interface Visitor {
    void visit(BooleanMessage booleanMessage);
    void visit(IntMessage intMessage);
    void visit(ArrayMessage arrayMessage);
    void visit(ByteMessage byteMessage);
    void visit(CharMessage charMessage);
    void visit(MultiDimArrayMessage multiDimArrayMessage);
    void visit(ObjectMessage objectMessage);
    void visit(StringMessage stringMessage);
    void visit(TwoDimArrayMessage twoDimArrayMessage);
    void visit(VarArgsMessage varArgsMessage);
}
