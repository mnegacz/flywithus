package com.flywithus.flight.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class Code {

    private final String code;

    private Code(String code) {
        this.code = code;
    }

    String code() {
        return code;
    }

    static Code of(String code) {
        assertNotNull(code, "code");

        return new Code(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Code code1 = (Code) o;

        return code.equals(code1.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

}
