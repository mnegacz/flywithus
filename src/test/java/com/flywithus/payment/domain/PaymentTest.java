package com.flywithus.payment.domain;

import static com.flywithus.payment.domain.PaymentStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

import com.flywithus.payment.exception.CannotExpirePaymentException;
import com.flywithus.payment.exception.CannotReceivePaymentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentTest {

  private static final int ONE_DAY = 1;
  private static final int TWO_DAYS = 2;

  @Mock private PaymentId paymentId;

  @Mock private Reservation reservation;

  @Mock private DateTime reservationDateTime;

  @Mock private DateTime now;

  @Test
  public void shouldBeReceivable() {
    // given
    Payment testee = new Payment(paymentId, reservation, CREATED);

    // when
    boolean result = testee.canBeReceived();

    // then
    assertThat(result).isTrue();
  }

  @Test
  public void shouldNotBeReceivableWhenAlreadyReceived() {
    // given
    Payment testee = new Payment(paymentId, reservation, RECEIVED);

    // when
    boolean result = testee.canBeReceived();

    // then
    assertThat(result).isFalse();
  }

  @Test
  public void shouldNotBeReceivableWhenExpired() {
    // given
    Payment testee = new Payment(paymentId, reservation, EXPIRED);

    // when
    boolean result = testee.canBeReceived();

    // then
    assertThat(result).isFalse();
  }

  @Test
  public void shouldReceivePayment() {
    // given
    Payment testee = new Payment(paymentId, reservation, CREATED);

    // when
    testee.receive();

    // then
    assertThat(testee.status()).isEqualTo(RECEIVED);
  }

  @Test
  public void shouldThrowCannotReceivePaymentExceptionWhenPaymentIsReceivedInNotReceivableState() {
    // given
    Payment testee = new Payment(paymentId, reservation, EXPIRED);

    // when
    Throwable result = catchThrowable(() -> testee.receive());

    // then
    assertThat(result).isInstanceOf(CannotReceivePaymentException.class);
  }

  @Test
  public void shouldBeExpirable() {
    // given
    Payment testee = new Payment(paymentId, reservation, CREATED);

    given(reservation.dateTime()).willReturn(reservationDateTime);
    given(now.differenceInDaysBetween(reservationDateTime)).willReturn(TWO_DAYS);

    // when
    boolean result = testee.canBeExpired(now);

    // then
    assertThat(result).isTrue();
  }

  @Test
  public void shouldNotBeExpirableWhenPaymentIsReceived() {
    // given
    Payment testee = new Payment(paymentId, reservation, RECEIVED);

    // when
    boolean result = testee.canBeExpired(now);

    // then
    assertThat(result).isFalse();
  }

  @Test
  public void shouldNotBeExpirableWhenPaymentIsAlreadyExpired() {
    // given
    Payment testee = new Payment(paymentId, reservation, EXPIRED);

    // when
    boolean result = testee.canBeExpired(now);

    // then
    assertThat(result).isFalse();
  }

  @Test
  public void shouldNotBeExpirableBeforeTimeIsExceeded() {
    // given
    Payment testee = new Payment(paymentId, reservation, CREATED);

    given(reservation.dateTime()).willReturn(reservationDateTime);
    given(now.differenceInDaysBetween(reservationDateTime)).willReturn(ONE_DAY);

    // when
    boolean result = testee.canBeExpired(now);

    // then
    assertThat(result).isFalse();
  }

  @Test
  public void shouldExpirePayment() {
    // given
    Payment testee = new Payment(paymentId, reservation, CREATED);

    given(reservation.dateTime()).willReturn(reservationDateTime);
    given(now.differenceInDaysBetween(reservationDateTime)).willReturn(TWO_DAYS);

    // when
    testee.expire(now);

    // then
    assertThat(testee.status()).isEqualTo(EXPIRED);
  }

  @Test
  public void shouldThrowCannotExpirePaymentExceptionWhenPaymentIsExpiredInNotExpirableState() {
    // given
    Payment testee = new Payment(paymentId, reservation, RECEIVED);

    // when
    Throwable result = catchThrowable(() -> testee.expire(now));

    // then
    assertThat(result).isInstanceOf(CannotExpirePaymentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenNowIsNull() {
    // given
    Payment testee = new Payment(paymentId, reservation, CREATED);
    DateTime now = null;

    // when
    Throwable result = catchThrowable(() -> testee.expire(now));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
