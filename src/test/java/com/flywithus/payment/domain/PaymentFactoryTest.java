package com.flywithus.payment.domain;

import static com.flywithus.payment.domain.PaymentStatus.CREATED;
import static com.flywithus.payment.domain.PaymentStatus.EXPIRED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentFactoryTest {

  @Mock private PaymentId paymentId;

  @Mock private Reservation reservation;

  private PaymentFactory testee = new PaymentFactory();

  @Test
  public void shouldCreateNewPayment() {
    // when
    Payment result = testee.createPayment(paymentId, reservation);

    // then
    assertThat(result).isNotNull();
    assertThat(result.id()).isEqualTo(paymentId);
    assertThat(result.reservation()).isEqualTo(reservation);
    assertThat(result.status()).isEqualTo(CREATED);
  }

  @Test
  public void shouldReturnPaymentWithStatus() {
    // given
    PaymentStatus status = EXPIRED;

    // when
    Payment result = testee.createPayment(paymentId, reservation, status);

    // then
    assertThat(result).isNotNull();
    assertThat(result.status()).isEqualTo(status);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
    // given
    PaymentId anotherId = null;

    // when
    Throwable result = catchThrowable(() -> testee.createPayment(anotherId, reservation));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenReservationIsNull() {
    // given
    Reservation anotherReservation = null;

    // when
    Throwable result = catchThrowable(() -> testee.createPayment(paymentId, anotherReservation));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenStatusIsNull() {
    // given
    PaymentStatus anotherStatus = null;

    // when
    Throwable result =
        catchThrowable(() -> testee.createPayment(paymentId, reservation, anotherStatus));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
