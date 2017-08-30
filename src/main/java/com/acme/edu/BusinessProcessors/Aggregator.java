package com.acme.edu.BusinessProcessors;

import com.acme.edu.Messages.*;

public class Aggregator implements Visitor {

    private LoggerState state = LoggerState.INIT_STATE;
    private LoggerState previousState = LoggerState.INIT_STATE;

    // Aggregated values
    private byte aggregatedByteValue;
    private String aggregatedStringValue;

    private IntMessage cachedIntMessage;
    private ByteMessage cachedByteMessage;
    private StringMessage cachedStringMessage;


    public void resetState() {

    }

    @Override
    public void visit(BooleanMessage booleanMessage) {
        state = LoggerState.BOOLEAN_INPUT;
    }

    @Override
    public void visit(IntMessage intMessage) {
        state = LoggerState.INT_INPUT;

        int aggregatedIntValue = 0;

        if (cachedIntMessage != null) {
            aggregatedIntValue = Integer.valueOf(cachedIntMessage.getMessage());
        } else {
            cachedIntMessage = intMessage;
        }

        int currentValue = Integer.valueOf(intMessage.getMessage());

        if (Integer.MAX_VALUE - aggregatedIntValue < currentValue) {  // overFlow
            intMessage.setMessage(String.valueOf(aggregatedIntValue));
            aggregatedIntValue = currentValue;
        } else if (Integer.MIN_VALUE + aggregatedIntValue > currentValue) {
            intMessage.setMessage(String.valueOf(aggregatedIntValue));
            aggregatedIntValue = currentValue;
        } else {
            aggregatedIntValue += currentValue;
        }

        cachedIntMessage.setMessage(String.valueOf(aggregatedIntValue));

    }

    @Override
    public void visit(ArrayMessage arrayMessage) {
        state = LoggerState.ARRAY_INPUT;
    }

    @Override
    public void visit(ByteMessage byteMessage) {
        state = LoggerState.BYTE_INPUT;
    }

    @Override
    public void visit(CharMessage charMessage) {
        state = LoggerState.CHAR_INPUT;
    }

    @Override
    public void visit(MultiDimArrayMessage multiDimArrayMessage) {
        state = LoggerState.MULTIDIM_ARRAY_INPUT;

    }

    @Override
    public void visit(ObjectMessage objectMessage) {
        state = LoggerState.OBJECT_INPUT;

    }

    @Override
    public void visit(StringMessage stringMessage) {
        state = LoggerState.STRING_INPUT;

    }

    @Override
    public void visit(TwoDimArrayMessage twoDimArrayMessage) {
        state = LoggerState.TWODIM_ARRAY_INPUT;

    }

    @Override
    public void visit(VarArgsMessage varArgsMessage) {
        state = LoggerState.VARARGS_ARRAY_INPUT;
    }

}
