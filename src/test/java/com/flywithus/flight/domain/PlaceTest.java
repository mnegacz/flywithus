package com.flywithus.flight.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;

public class PlaceTest {

  @Test
  public void shouldReturnPlace() {
    // given
    String name = "name";

    // when
    Place result = Place.of(name);

    // then
    assertThat(result).isNotNull();
    assertThat(result.name()).isEqualTo(name);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenNameIsNull() {
    // given
    String id = null;

    // when
    Throwable result = catchThrowable(() -> Place.of(id));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
