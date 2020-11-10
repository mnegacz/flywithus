package com.flywithus.payment.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReservationFactoryTest {

  @Mock private ReservationId id;

  @Mock private Money amount;

  @Mock private DateTime dateTime;

  @InjectMocks private ReservationFactory testee;

  @Test
  public void shouldReturnPayment() {
    // when
    Reservation result = testee.createReservation(id, amount, dateTime);

    // then
    assertThat(result).isNotNull();
    assertThat(result.id()).isEqualTo(id);
    assertThat(result.amount()).isEqualTo(amount);
    assertThat(result.dateTime()).isEqualTo(dateTime);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
    // given
    ReservationId anotherId = null;

    // when
    Throwable result = catchThrowable(() -> testee.createReservation(anotherId, amount, dateTime));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenAmountIsNull() {
    // given
    Money anotherAmount = null;

    // when
    Throwable result = catchThrowable(() -> testee.createReservation(id, anotherAmount, dateTime));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenDateTimeIsNull() {
    // given
    DateTime anotherDateTime = null;

    // when
    Throwable result = catchThrowable(() -> testee.createReservation(id, amount, anotherDateTime));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
