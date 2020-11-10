package com.flywithus.infrastructure.assertions;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import org.junit.Test;

public class ArgumentAssertionsTest {

  private static final String ARGUMENT_NAME = "argument name";

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenArgumentIsNull() {
    // given
    Object argument = null;

    // when
    Throwable result =
        catchThrowable(() -> ArgumentAssertions.assertNotNull(argument, ARGUMENT_NAME));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldNotThrowIllegalArgumentExceptionWhenArgumentIsNotNull() {
    // given
    Object argument = new Object();

    // when
    Throwable result =
        catchThrowable(() -> ArgumentAssertions.assertNotNull(argument, ARGUMENT_NAME));

    // then
    assertThat(result).isNull();
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenArgumentIsNegative() {
    // given
    BigDecimal argument = BigDecimal.valueOf(-1);

    // when
    Throwable result =
        catchThrowable(() -> ArgumentAssertions.assertNotNegative(argument, ARGUMENT_NAME));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldNotThrowIllegalArgumentExceptionWhenArgumentIsPositive() {
    // given
    BigDecimal argument = ONE;

    // when
    Throwable result =
        catchThrowable(() -> ArgumentAssertions.assertNotNegative(argument, ARGUMENT_NAME));

    // then
    assertThat(result).isNull();
  }

  @Test
  public void shouldNotThrowIllegalArgumentExceptionWhenArgumentIsZero() {
    // given
    BigDecimal argument = ZERO;

    // when
    Throwable result =
        catchThrowable(() -> ArgumentAssertions.assertNotNegative(argument, ARGUMENT_NAME));

    // then
    assertThat(result).isNull();
  }
}
