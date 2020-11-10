package com.flywithus.payment.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;

public class PaymentIdTest {

  @Test
  public void shouldReturnPaymentId() {
    // given
    String id = "payment id";

    // when
    PaymentId result = PaymentId.of(id);

    // then
    assertThat(result).isNotNull();
    assertThat(result.id()).isEqualTo(id);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
    // given
    String id = null;

    // when
    Throwable result = catchThrowable(() -> PaymentId.of(id));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
