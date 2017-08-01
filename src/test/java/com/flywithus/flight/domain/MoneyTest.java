package com.flywithus.flight.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class MoneyTest {

    @Test
    public void shouldReturnMoneyOfAmount() {
        // given
        BigDecimal amount = ONE;

        // when
        Money result = Money.of(amount);

        // then
        assertThat(result).isNotNull();
        assertThat(result.amount()).isEqualTo(amount);
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

}