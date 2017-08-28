package com.acme.edu.Buffers;

public enum LogBufferInputState {
    INIT_STATE(false),
    ARRAY_INPUT(false),
    BOOLEAN_INPUT(false),
    BYTE_INPUT(true),
    CHAR_INPUT(false),
    INT_INPUT(true),
    MULTIDIM_ARRAY_INPUT(false),
    OBJECT_INPUT(false),
    STRING_INPUT(true),
    TWODIM_ARRAY_INPUT(false),
    VARARGS_ARRAY_INPUT(false);


    private boolean bufferingOptionEnabled;

    private LogBufferInputState(boolean bufferingOptionEnabled){
        this.bufferingOptionEnabled = bufferingOptionEnabled;
    }

    public boolean isBufferingOptionEnabled() {
        return bufferingOptionEnabled;
    }
}
