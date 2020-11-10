package com.flywithus.payment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class DateTimeFactoryTest {

  private DateTimeFactory testee = new DateTimeFactory();

  @Test
  public void shouldReturnDateTime() {
    // when
    DateTime result = testee.now();

    // then
    assertThat(result).isNotNull();
  }
}
