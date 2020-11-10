package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static com.flywithus.reservation.domain.ReservationStatus.CANCELLED;

import com.flywithus.reservation.dto.FindReservationDTO;
import com.flywithus.reservation.dto.ReservationDTO;
import com.flywithus.reservation.exception.CannotCancelReservationException;
import com.flywithus.reservation.exception.CannotChangeReservationException;
import java.util.Optional;

class Reservation {

  private static final int FIVE_DAYS = 5;
  private static final int THREE_DAYS = 3;

  private ReservationId id;
  private Client client;
  private Flight flight;
  private DateTime dateTime;
  private NumberOfPeople numberOfPeople;
  private Discount discount;
  private ReservationStatus status;

  Reservation(
      ReservationId id,
      Client client,
      Flight flight,
      DateTime dateTime,
      NumberOfPeople numberOfPeople,
      ReservationStatus status) {
    this.id = id;
    this.client = client;
    this.flight = flight;
    this.dateTime = dateTime;
    this.numberOfPeople = numberOfPeople;
    this.status = status;
  }

  void applyDiscount(Discount discount) {
    assertNotNull(discount, "discount");

    this.discount = discount;
  }

  void change(Flight flight, NumberOfPeople numberOfPeople, DateTime now) {
    assertNotNull(flight, "flight");
    assertNotNull(numberOfPeople, "numberOfPeople");

    if (!canBeChanged(now)) {
      throw new CannotChangeReservationException();
    }

    this.flight = flight;
    this.numberOfPeople = numberOfPeople;
  }

  private boolean canBeChanged(DateTime now) {
    return isNotCancelled() && moreThanThreeDaysLeftToDepartureDateTime(now);
  }

  void cancel(DateTime now) {
    if (!canBeCancelled(now)) {
      throw new CannotCancelReservationException();
    }
    this.status = CANCELLED;
  }

  private boolean canBeCancelled(DateTime now) {
    return isNotCancelled() && moreThanFiveDaysLeftToDepartureDateTime(now);
  }

  private boolean moreThanThreeDaysLeftToDepartureDateTime(DateTime now) {
    return moreThanXDaysLeftToDepartureDateTime(now, THREE_DAYS);
  }

  private boolean moreThanFiveDaysLeftToDepartureDateTime(DateTime now) {
    return moreThanXDaysLeftToDepartureDateTime(now, FIVE_DAYS);
  }

  private boolean moreThanXDaysLeftToDepartureDateTime(DateTime now, int xDays) {
    assertNotNull(now, "now");

    DateTime reservationDateTime = flight.departureDateTime();
    int differenceInDays = now.differenceInDaysBetween(reservationDateTime);
    return differenceInDays > xDays;
  }

  private boolean isNotCancelled() {
    return !CANCELLED.equals(status);
  }

  ReservationId id() {
    return id;
  }

  Client client() {
    return client;
  }

  Flight flight() {
    return flight;
  }

  DateTime dateTime() {
    return dateTime;
  }

  NumberOfPeople numberOfPeople() {
    return numberOfPeople;
  }

  Optional<Discount> discount() {
    return Optional.ofNullable(discount);
  }

  ReservationStatus status() {
    return status;
  }

  ReservationDTO toDTO() {
    ReservationDTO dto =
        new ReservationDTO(
            id.id(),
            client.toDTO(),
            flight.toDTO(),
            numberOfPeople.number(),
            dateTime.value(),
            status.toDTO());

    discount().map(Discount::toDTO).ifPresent(dto::setDiscount);

    return dto;
  }

  FindReservationDTO toFindReservationDTO() {
    Money price =
        discount().map(discount -> flight.price().discountBy(discount)).orElse(flight.price());

    return new FindReservationDTO(id.id(), price.amount(), dateTime.value());
  }
}
