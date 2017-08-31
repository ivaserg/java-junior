package com.acme.edu;

public class LoggerState {
    private State currentState = State.INIT_STATE;
    private State previousState  = State.INIT_STATE;

    public State getCurrentState() {
        return currentState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setCurrentState(State currentState) {
        if (previousState == State.INIT_STATE) {
            this.previousState=currentState;
            this.currentState = currentState;
        } else {
            this.previousState=this.currentState;
            this.currentState = currentState;
        }

    }

    public boolean isStateSwitched() {
        return (currentState!=previousState);
    }

}

