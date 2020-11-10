package com.flywithus.flight.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.time.LocalDateTime;
import org.junit.Test;

public class DateTimeTest {

  @Test
  public void shouldReturnDateTime() {
    // given
    LocalDateTime value = LocalDateTime.parse("2017-01-01T08:00:00");

    // when
    DateTime result = DateTime.of(value);

    // then
    assertThat(result).isNotNull();
    assertThat(result.value()).isEqualTo(value);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenValueIsNull() {
    // given
    LocalDateTime value = null;

    // when
    Throwable result = catchThrowable(() -> DateTime.of(value));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
