package com.flywithus.payment.domain;

import java.math.BigDecimal;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class Money {

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    BigDecimal amount() {
        return amount;
    }

    static Money of(BigDecimal amount) {
        assertNotNull(amount, "amount");

        return new Money(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        return amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

}
