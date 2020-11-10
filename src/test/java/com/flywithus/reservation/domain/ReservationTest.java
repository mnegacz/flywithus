package com.flywithus.reservation.domain;

import static com.flywithus.reservation.domain.ReservationStatus.CANCELLED;
import static com.flywithus.reservation.domain.ReservationStatus.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

import com.flywithus.reservation.exception.CannotCancelReservationException;
import com.flywithus.reservation.exception.CannotChangeReservationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReservationTest {

  private static final int TWO_DAYS = 2;
  private static final int THREE_DAYS = 3;
  private static final int FOUR_DAYS = 4;
  private static final int FIVE_DAYS = 5;
  private static final int SIX_DAYS = 6;

  @Mock private ReservationId id;

  @Mock private Client client;

  @Mock private Discount discount;

  @Mock private Flight flight;

  @Mock private DateTime dateTime;

  @Mock private NumberOfPeople numberOfPeople;

  @Mock private DateTime now;

  @Mock private DateTime departureDateTime;

  @Before
  public void setUp() {
    given(flight.departureDateTime()).willReturn(departureDateTime);
  }

  @Test
  public void shouldApplyDiscount() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    // when
    testee.applyDiscount(discount);

    // then
    assertThat(testee.discount()).contains(discount);
  }

  @Test
  public void shouldApplyDiscountThrowIllegalArgumentExceptionWhenDiscountIsNull() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);
    Discount anotherDiscount = null;

    // when
    Throwable result = catchThrowable(() -> testee.applyDiscount(anotherDiscount));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldChange() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    given(now.differenceInDaysBetween(departureDateTime)).willReturn(FOUR_DAYS);

    // when
    testee.change(flight, numberOfPeople, now);

    // then
    assertThat(testee.flight()).isEqualTo(flight);
    assertThat(testee.numberOfPeople()).isEqualTo(numberOfPeople);
  }

  @Test
  public void shouldChangeThrowCannotChangeExceptionWhenReservationIsAlreadyCancelled() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CANCELLED);

    // when
    Throwable result = catchThrowable(() -> testee.change(flight, numberOfPeople, now));

    // then
    assertThat(result).isInstanceOf(CannotChangeReservationException.class);
  }

  @Test
  public void shouldChangeThrowCannotChangeExceptionWhenItIsThreeDaysToDepartureDateTime() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    given(now.differenceInDaysBetween(departureDateTime)).willReturn(THREE_DAYS);

    // when
    Throwable result = catchThrowable(() -> testee.change(flight, numberOfPeople, now));

    // then
    assertThat(result).isInstanceOf(CannotChangeReservationException.class);
  }

  @Test
  public void shouldChangeThrowCannotChangeExceptionWhenItIsLessThanThreeDaysToDepartureDateTime() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    given(now.differenceInDaysBetween(departureDateTime)).willReturn(TWO_DAYS);

    // when
    Throwable result = catchThrowable(() -> testee.change(flight, numberOfPeople, now));

    // then
    assertThat(result).isInstanceOf(CannotChangeReservationException.class);
  }

  @Test
  public void shouldChangeThrowIllegalArgumentExceptionWhenFlightIsNull() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    Flight anotherFlight = null;

    // when
    Throwable result = catchThrowable(() -> testee.change(anotherFlight, numberOfPeople, now));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldChangeThrowIllegalArgumentExceptionWhenNumberOfPeopleIsNull() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    NumberOfPeople anotherNumberOfPeople = null;

    // when
    Throwable result = catchThrowable(() -> testee.change(flight, anotherNumberOfPeople, now));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldChangeThrowIllegalArgumentExceptionWhenNowIsNull() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    DateTime anotherNow = null;

    // when
    Throwable result = catchThrowable(() -> testee.change(flight, numberOfPeople, anotherNow));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldCancel() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    given(now.differenceInDaysBetween(departureDateTime)).willReturn(SIX_DAYS);

    // when
    testee.cancel(now);

    // then
    assertThat(testee.status()).isEqualTo(CANCELLED);
  }

  @Test
  public void shouldCancelThrowCannotCancelExceptionWhenReservationIsAlreadyCancelled() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CANCELLED);

    // when
    Throwable result = catchThrowable(() -> testee.cancel(now));

    // then
    assertThat(result).isInstanceOf(CannotCancelReservationException.class);
  }

  @Test
  public void shouldCancelThrowCannotCancelExceptionWhenItIsFiveDaysToDeparture() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    given(now.differenceInDaysBetween(departureDateTime)).willReturn(FIVE_DAYS);

    // when
    Throwable result = catchThrowable(() -> testee.cancel(now));

    // then
    assertThat(result).isInstanceOf(CannotCancelReservationException.class);
  }

  @Test
  public void shouldCancelThrowCannotCancelExceptionWhenItIsLessThanFiveDaysToDeparture() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    given(now.differenceInDaysBetween(departureDateTime)).willReturn(FOUR_DAYS);

    // when
    Throwable result = catchThrowable(() -> testee.cancel(now));

    // then
    assertThat(result).isInstanceOf(CannotCancelReservationException.class);
  }

  @Test
  public void shouldCancelThrowIllegalArgumentExceptionWhenNowIsNull() {
    // given
    Reservation testee = new Reservation(id, client, flight, dateTime, numberOfPeople, CREATED);

    DateTime anotherNow = null;

    // when
    Throwable result = catchThrowable(() -> testee.cancel(anotherNow));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
