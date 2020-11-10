package com.flywithus.flight.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import java.time.LocalDateTime;

class DateTime {

  private final LocalDateTime value;

  private DateTime(LocalDateTime value) {
    this.value = value;
  }

  LocalDateTime value() {
    return value;
  }

  static DateTime of(LocalDateTime value) {
    assertNotNull(value, "value");

    return new DateTime(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DateTime dateTime1 = (DateTime) o;

    return value.equals(dateTime1.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
