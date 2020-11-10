package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import com.flywithus.reservation.dto.DiscountDTO;

class Discount {

  private final Percent percent;

  private Discount(Percent percent) {
    this.percent = percent;
  }

  Percent percent() {
    return percent;
  }

  static Discount of(Percent raw) {
    assertNotNull(raw, "percent");
    assertNotGreaterThanOneHundred(raw);

    return new Discount(raw);
  }

  private static void assertNotGreaterThanOneHundred(Percent raw) {
    if (raw.isGreaterThan(Percent.of(100))) {
      throw new IllegalArgumentException("percent is greater than 100");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Discount discount = (Discount) o;

    return percent.equals(discount.percent);
  }

  @Override
  public int hashCode() {
    return percent.hashCode();
  }

  DiscountDTO toDTO() {
    return new DiscountDTO(percent.value());
  }
}
