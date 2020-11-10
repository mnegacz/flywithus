package com.flywithus.reservation.domain;

import static com.flywithus.reservation.domain.ReservationStatus.CANCELLED;
import static com.flywithus.reservation.domain.ReservationStatus.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReservationFactoryTest {

  @Mock private ReservationId reservationId;

  @Mock private Client client;

  @Mock private Flight flight;

  @Mock private DateTime dateTime;

  @Mock private NumberOfPeople numberOfPeople;

  @Mock private DateTimeFactory dateTimeFactory;

  @Mock private DiscountPolicy discountPolicy;

  @Mock private Discount discount;

  @InjectMocks private ReservationFactory testee;

  @Test
  public void shouldCreateNewReservationWithoutDiscount() {
    // given
    given(dateTimeFactory.now()).willReturn(dateTime);

    // when
    Reservation result = testee.createReservation(client, flight, numberOfPeople);

    // then
    assertThat(result).isNotNull();
    assertThat(result.id()).isNotNull();
    assertThat(result.client()).isEqualTo(client);
    assertThat(result.flight()).isEqualTo(flight);
    assertThat(result.numberOfPeople()).isEqualTo(numberOfPeople);
    assertThat(result.dateTime()).isEqualTo(dateTime);
    assertThat(result.status()).isEqualTo(CREATED);
  }

  @Test
  public void shouldCreateNewReservationWithDiscount() {
    // given
    given(dateTimeFactory.now()).willReturn(dateTime);
    given(discountPolicy.findDiscount(client)).willReturn(Optional.of(discount));

    // when
    Reservation result = testee.createReservation(client, flight, numberOfPeople);

    // then
    assertThat(result.discount()).contains(discount);
  }

  @Test
  public void shouldReturnReservation() {
    // when
    Reservation result =
        testee.createReservation(
            reservationId,
            client,
            flight,
            dateTime,
            numberOfPeople,
            Optional.of(discount),
            CANCELLED);

    // then
    assertThat(result).isNotNull();
    assertThat(result.id()).isEqualTo(reservationId);
    assertThat(result.client()).isEqualTo(client);
    assertThat(result.flight()).isEqualTo(flight);
    assertThat(result.dateTime()).isEqualTo(dateTime);
    assertThat(result.numberOfPeople()).isEqualTo(numberOfPeople);
    assertThat(result.dateTime()).isEqualTo(dateTime);
    assertThat(result.discount()).contains(discount);
    assertThat(result.status()).isEqualTo(CANCELLED);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
    // given
    ReservationId anotherId = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createReservation(
                    anotherId,
                    client,
                    flight,
                    dateTime,
                    numberOfPeople,
                    Optional.of(discount),
                    CANCELLED));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
    // given
    Client anotherClient = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createReservation(
                    reservationId,
                    anotherClient,
                    flight,
                    dateTime,
                    numberOfPeople,
                    Optional.of(discount),
                    CANCELLED));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenFlightIsNull() {
    // given
    Flight anotherFlight = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createReservation(
                    reservationId,
                    client,
                    anotherFlight,
                    dateTime,
                    numberOfPeople,
                    Optional.of(discount),
                    CANCELLED));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenDateTimeIsNull() {
    // given
    DateTime anotherDateTime = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createReservation(
                    reservationId,
                    client,
                    flight,
                    anotherDateTime,
                    numberOfPeople,
                    Optional.of(discount),
                    CANCELLED));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenNumberOfPeopleIsNull() {
    // given
    NumberOfPeople anotherNumberOfPeople = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createReservation(
                    reservationId,
                    client,
                    flight,
                    dateTime,
                    anotherNumberOfPeople,
                    Optional.of(discount),
                    CANCELLED));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenDiscountOptionalIsNull() {
    // given
    Optional<Discount> discountOptional = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createReservation(
                    reservationId,
                    client,
                    flight,
                    dateTime,
                    numberOfPeople,
                    discountOptional,
                    CANCELLED));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenStatusIsNull() {
    // given
    ReservationStatus anotherStatus = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createReservation(
                    reservationId,
                    client,
                    flight,
                    dateTime,
                    numberOfPeople,
                    Optional.of(discount),
                    anotherStatus));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
