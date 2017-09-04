package com.acme.edu;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class MessageEventHandlerStateTest {

    private MessageEventHandlerState sut = new MessageEventHandlerState();

    @Test
    public void shouldNotChangeStateWhenFirstInputMessageArrived() {
        sut.switchState(State.STRING_INPUT);

        assertThat(sut.isStateSwitched()).isEqualTo(false);
    }

    @Test
    public void shouldChangeCurrentAndPreviousStateToTheGivenWhenInitialMessageArrived() {
        sut.switchState(State.STRING_INPUT);

        assertThat(sut.getCurrentState()).isEqualTo(State.STRING_INPUT);
        assertThat(sut.getPreviousState()).isEqualTo(State.STRING_INPUT);
    }

    @Test
    public void shouldNotChangeStateWhenSubsequentInputMessageArrived() {
        sut.switchState(State.STRING_INPUT);
        sut.switchState(State.INT_INPUT);

        assertThat(sut.isStateSwitched()).isEqualTo(true);
    }

    @Test
    public void shouldSwitchToTheGivenStateWhenMessageArrived() {
        sut.switchState(State.INT_INPUT);
        sut.switchState(State.STRING_INPUT);

        assertThat(sut.getCurrentState()).isEqualTo(State.STRING_INPUT);
    }


}
