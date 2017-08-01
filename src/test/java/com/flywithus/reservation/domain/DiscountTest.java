package com.flywithus.reservation.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class DiscountTest {

    @Test
    public void shouldReturnDiscountOfPercent() {
        // given
        Percent percent = Percent.of(1);

        // when
        Discount result = Discount.of(percent);

        // then
        assertThat(result).isNotNull();
        assertThat(result.percent()).isEqualTo(percent);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenPercentIsNull() {
        // given
        Percent percent = null;

        // when
        Throwable result = catchThrowable(() -> Discount.of(percent));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenPercentIsOverOneHundred() {
        // given
        Percent percent = Percent.of(101);

        // when
        Throwable result = catchThrowable(() -> Discount.of(percent));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}