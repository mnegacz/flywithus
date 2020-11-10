package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static com.flywithus.reservation.domain.ReservationStatus.CREATED;

import java.util.Optional;

class ReservationFactory {

  private final DateTimeFactory dateTimeFactory;
  private final DiscountPolicy discountPolicy;

  ReservationFactory(DateTimeFactory dateTimeFactory, DiscountPolicy discountPolicy) {
    this.dateTimeFactory = dateTimeFactory;
    this.discountPolicy = discountPolicy;
  }

  Reservation createReservation(Client client, Flight flight, NumberOfPeople numberOfPeople) {
    assertNotNull(client, "client");
    assertNotNull(flight, "flight");
    assertNotNull(numberOfPeople, "numberOfPeople");

    ReservationId id = ReservationId.generate();
    DateTime now = dateTimeFactory.now();
    Optional<Discount> discount = discountPolicy.findDiscount(client);

    return createReservation(id, client, flight, now, numberOfPeople, discount, CREATED);
  }

  Reservation createReservation(
      ReservationId id,
      Client client,
      Flight flight,
      DateTime dateTime,
      NumberOfPeople numberOfPeople,
      Optional<Discount> discount,
      ReservationStatus status) {
    assertNotNull(id, "id");
    assertNotNull(client, "client");
    assertNotNull(flight, "flight");
    assertNotNull(dateTime, "dateTime");
    assertNotNull(discount, "discount");
    assertNotNull(numberOfPeople, "numberOfPeople");
    assertNotNull(status, "status");

    Reservation reservation = new Reservation(id, client, flight, dateTime, numberOfPeople, status);

    discount.ifPresent(reservation::applyDiscount);

    return reservation;
  }
}
