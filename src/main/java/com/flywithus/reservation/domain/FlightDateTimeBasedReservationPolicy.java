package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class FlightDateTimeBasedReservationPolicy {

  private DateTimeFactory dateTimeFactory;

  boolean isImpossibleLaterThanXDaysBeforeFlightDepartureDateTime(
      Reservation reservation, int days) {
    assertNotNull(reservation, "reservation");

    int differenceInDays =
        dateTimeFactory.now().differenceInDaysBetween(reservation.flight().departureDateTime());
    return differenceInDays < days;
  }
}
