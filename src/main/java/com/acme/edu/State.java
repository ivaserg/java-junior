package com.acme.edu;

enum State {
    INIT_STATE(""),
    INT_STATE("primitive: "),
    STRING_STATE("string: "),
    BYTE_STATE("primitive: "),
    CHAR_STATE("char: "),
    BOOLEAN_STATE("primitive: "),
    PRIMITIVE_ARRAY_STATE("primitives array: "),
    PRIMITIVES_MATRIX_ARRAY_STATE("primitives matrix: "),
    PRIMITIVES_MULTIMATRIX_ARRAY_STATE("primitives multimatrix: "),
    VARARGS_STATE(""),
    OBJECT_STATE("reference: ");

    private final String relevantTypeDescription;

    String getRelevantTypeDescription() {
        return relevantTypeDescription;
    }

    State(String relevantTypeDescription) {
        this.relevantTypeDescription = relevantTypeDescription;
    }
}
