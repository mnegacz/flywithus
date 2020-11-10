package com.flywithus.flight.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FlightFactoryTest {

  @Mock private FlightId id;

  @Mock private Code code;

  @Mock private Place departurePlace;

  @Mock private DateTime departureDateTime;

  @Mock private Place arrivalPlace;

  @Mock private DateTime arrivalDateTime;

  @Mock private Capacity availableCapacity;

  @Mock private Money price;

  private FlightFactory testee = new FlightFactory();

  @Test
  public void shouldCreateNewFlight() {
    // when
    Flight result =
        testee.createFlight(
            id,
            code,
            departurePlace,
            departureDateTime,
            arrivalPlace,
            arrivalDateTime,
            availableCapacity,
            price);

    // then
    assertThat(result).isNotNull();
    assertThat(result.id()).isEqualTo(id);
    assertThat(result.code()).isEqualTo(code);
    assertThat(result.departurePlace()).isEqualTo(departurePlace);
    assertThat(result.departureDateTime()).isEqualTo(departureDateTime);
    assertThat(result.arrivalPlace()).isEqualTo(arrivalPlace);
    assertThat(result.arrivalDateTime()).isEqualTo(arrivalDateTime);
    assertThat(result.availableCapacity()).isEqualTo(availableCapacity);
    assertThat(result.price()).isEqualTo(price);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
    // given
    FlightId anotherId = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createFlight(
                    anotherId,
                    code,
                    departurePlace,
                    departureDateTime,
                    arrivalPlace,
                    arrivalDateTime,
                    availableCapacity,
                    price));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenCodeIsNull() {
    // given
    Code anotherCode = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createFlight(
                    id,
                    anotherCode,
                    departurePlace,
                    departureDateTime,
                    arrivalPlace,
                    arrivalDateTime,
                    availableCapacity,
                    price));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenDeparturePlaceIsNull() {
    // given
    Place anotherPlace = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createFlight(
                    id,
                    code,
                    anotherPlace,
                    departureDateTime,
                    arrivalPlace,
                    arrivalDateTime,
                    availableCapacity,
                    price));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenDepartureDateTimeIsNull() {
    // given
    DateTime anotherDateTime = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createFlight(
                    id,
                    code,
                    departurePlace,
                    anotherDateTime,
                    arrivalPlace,
                    arrivalDateTime,
                    availableCapacity,
                    price));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenArrivalPlaceIsNull() {
    // given
    Place anotherPlace = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createFlight(
                    id,
                    code,
                    departurePlace,
                    departureDateTime,
                    anotherPlace,
                    arrivalDateTime,
                    availableCapacity,
                    price));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenArrivalDateTimeIsNull() {
    // given
    DateTime anotherDateTime = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createFlight(
                    id,
                    code,
                    departurePlace,
                    departureDateTime,
                    arrivalPlace,
                    anotherDateTime,
                    availableCapacity,
                    price));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenAvailableCapacityIsNull() {
    // given
    Capacity anotherCapacity = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createFlight(
                    id,
                    code,
                    departurePlace,
                    departureDateTime,
                    arrivalPlace,
                    arrivalDateTime,
                    anotherCapacity,
                    price));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenPriceIsNull() {
    // given
    Money anotherPrice = null;

    // when
    Throwable result =
        catchThrowable(
            () ->
                testee.createFlight(
                    id,
                    code,
                    departurePlace,
                    departureDateTime,
                    arrivalPlace,
                    arrivalDateTime,
                    availableCapacity,
                    anotherPrice));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
