package com.acme.edu;

import com.acme.edu.Messages.Message;

/**
 * Created by vanbkin on 30.08.2017.
 */
public class LoggerState {
    public State currentState;
    public State previousState;

    public Message lastMessage;

    public LoggerState() {
        currentState=State.INIT_STATE;
        previousState=State.INIT_STATE;
    }
    
private enum State {
    INIT_STATE,
    ARRAY_INPUT,
    BOOLEAN_INPUT,
    BYTE_INPUT,
    CHAR_INPUT,
    INT_INPUT,
    MULTIDIM_ARRAY_INPUT,
    OBJECT_INPUT,
    STRING_INPUT,
    TWODIM_ARRAY_INPUT,
    VARARGS_ARRAY_INPUT;

}   
    
}
