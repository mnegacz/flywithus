package com.flywithus.reservation.domain;

import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import org.junit.Test;

public class PercentTest {

  @Test
  public void shouldReturnPercent() {
    // given
    int value = 1;

    // when
    Percent result = Percent.of(value);

    // then
    assertThat(result).isNotNull();
    assertThat(result.value()).isEqualTo(ONE);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenValueIsNull() {
    // given
    BigDecimal value = null;

    // when
    Throwable result = catchThrowable(() -> Percent.of(value));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenValueIsNegative() {
    // given
    int value = -1;

    // when
    Throwable result = catchThrowable(() -> Percent.of(value));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
