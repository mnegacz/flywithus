package com.flywithus.reservation.domain;

import java.time.LocalDateTime;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static java.time.temporal.ChronoUnit.DAYS;

class DateTime {

    private final LocalDateTime value;

    private DateTime(LocalDateTime value) {
        this.value = value;
    }

    LocalDateTime value() {
        return value;
    }

    int differenceInDaysBetween(DateTime other) {
        assertNotNull(other, "other");

        return Math.abs((int) DAYS.between(value, other.value));
    }

    static DateTime of(LocalDateTime value) {
        assertNotNull(value, "value");

        return new DateTime(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateTime dateTime = (DateTime) o;

        return value.equals(dateTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
