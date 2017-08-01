package com.flywithus.reservation.domain;

import java.math.BigDecimal;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNegative;
import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class Percent {

    private final BigDecimal value;

    private Percent(BigDecimal value) {
        this.value = value;
    }

    BigDecimal value() {
        return value;
    }

    boolean isGreaterThan(Percent percent) {
        return value.compareTo(percent.value) > 0;
    }

    static Percent of(int value) {
        return Percent.of(BigDecimal.valueOf(value));
    }

    static Percent of(BigDecimal value) {
        assertNotNull(value, "value");
        assertNotNegative(value, "value");

        return new Percent(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Percent percent = (Percent) o;

        return value.equals(percent.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
