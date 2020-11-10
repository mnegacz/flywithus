package com.flywithus.reservation.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FlightDateTimeBasedReservationPolicyTest {

  private static final int TWO_DAYS = 2;
  private static final int THREE_DAYS = 3;
  private static final int FOUR_DAYS = 4;

  @Mock private DateTimeFactory dateTimeFactory;

  @Mock private Reservation reservation;

  @Mock private Flight flight;

  @Mock private DateTime departureDateTime;

  @Mock private DateTime now;

  @InjectMocks private FlightDateTimeBasedReservationPolicy testee;

  @Before
  public void setUp() {
    given(dateTimeFactory.now()).willReturn(now);
    given(reservation.flight()).willReturn(flight);
    given(flight.departureDateTime()).willReturn(departureDateTime);
  }

  @Test
  public void shouldBePossibleWhenItIsMoreThanThreeDaysToFlightDeparture() {
    // given
    given(now.differenceInDaysBetween(departureDateTime)).willReturn(FOUR_DAYS);

    // when
    boolean result =
        testee.isImpossibleLaterThanXDaysBeforeFlightDepartureDateTime(reservation, THREE_DAYS);

    // then
    assertThat(result).isFalse();
  }

  @Test
  public void shouldBeImpossibleWhenItIsLessThanThreeDaysToFlightDeparture() {
    // given
    given(now.differenceInDaysBetween(departureDateTime)).willReturn(TWO_DAYS);

    // when
    boolean result =
        testee.isImpossibleLaterThanXDaysBeforeFlightDepartureDateTime(reservation, THREE_DAYS);

    // then
    assertThat(result).isTrue();
  }

  @Test
  public void shouldBePossibleWhenItIsThreeDaysToFlightDeparture() {
    // given
    given(now.differenceInDaysBetween(departureDateTime)).willReturn(THREE_DAYS);

    // when
    boolean result =
        testee.isImpossibleLaterThanXDaysBeforeFlightDepartureDateTime(reservation, THREE_DAYS);

    // then
    assertThat(result).isFalse();
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenReservationIsNull() {
    // given
    Reservation anotherReservation = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.isImpossibleLaterThanXDaysBeforeFlightDepartureDateTime(
                    anotherReservation, THREE_DAYS));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
