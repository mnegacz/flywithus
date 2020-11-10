package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNegative;
import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

class Money {

  private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

  private final BigDecimal amount;

  private Money(BigDecimal amount) {
    this.amount = amount;
  }

  Money discountBy(Discount discount) {
    assertNotNull(discount, "discount");

    Percent percent = discount.percent();
    BigDecimal amountOfDiscount = amount.multiply(percent.value()).divide(ONE_HUNDRED, HALF_UP);
    BigDecimal amountAfterDiscount = this.amount.subtract(amountOfDiscount);

    return Money.of(amountAfterDiscount);
  }

  BigDecimal amount() {
    return amount;
  }

  static Money of(BigDecimal amount) {
    assertNotNull(amount, "amount");
    assertNotNegative(amount, "amount");

    return new Money(amount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Money money = (Money) o;

    return amount != null ? amount.equals(money.amount) : money.amount == null;
  }

  @Override
  public int hashCode() {
    return amount != null ? amount.hashCode() : 0;
  }
}
