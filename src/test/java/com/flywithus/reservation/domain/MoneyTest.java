package com.flywithus.reservation.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class MoneyTest {

    private static final BigDecimal MINUS_ONE = BigDecimal.valueOf(-1);
    private static final BigDecimal TWO = BigDecimal.valueOf(2);

    @Test
    public void shouldReturnMoneyOfAmount() {
        // given
        BigDecimal amount = ONE;

        // when
        Money result = Money.of(amount);

        // then
        assertThat(result.amount()).isEqualTo(ONE);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountIsNegative() {
        // given
        BigDecimal amount = MINUS_ONE;

        // when
        Throwable result = catchThrowable(() -> Money.of(amount));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountIsNull() {
        // given
        BigDecimal amount = null;

        // when
        Throwable result = catchThrowable(() -> Money.of(amount));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldDiscountMoneyWithPercent() {
        // given
        Money money = Money.of(TWO);
        Discount discount = Discount.of(Percent.of(50));

        // when
        Money result = money.discountBy(discount);

        // then
        assertThat(result).isEqualTo(Money.of(ONE));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDiscountIsNull() {
        // given
        Money money = Money.of(TWO);
        Discount discount = null;

        // when
        Throwable result = catchThrowable(() -> money.discountBy(discount));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}