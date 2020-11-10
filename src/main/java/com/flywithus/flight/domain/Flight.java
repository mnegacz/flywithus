package com.flywithus.flight.domain;

import com.flywithus.flight.dto.FindFlightDTO;
import com.flywithus.flight.dto.SearchFlightDTO;

class Flight {

  private final FlightId id;
  private final Code code;
  private final Place departurePlace;
  private final DateTime departureDateTime;
  private final Place arrivalPlace;
  private final DateTime arrivalDateTime;
  private final Capacity availableCapacity;
  private final Money price;

  Flight(
      FlightId id,
      Code code,
      Place departurePlace,
      DateTime departureDateTime,
      Place arrivalPlace,
      DateTime arrivalDateTime,
      Capacity availableCapacity,
      Money price) {
    this.id = id;
    this.code = code;
    this.departurePlace = departurePlace;
    this.departureDateTime = departureDateTime;
    this.arrivalPlace = arrivalPlace;
    this.arrivalDateTime = arrivalDateTime;
    this.availableCapacity = availableCapacity;
    this.price = price;
  }

  FlightId id() {
    return id;
  }

  Code code() {
    return code;
  }

  Place departurePlace() {
    return departurePlace;
  }

  DateTime departureDateTime() {
    return departureDateTime;
  }

  Place arrivalPlace() {
    return arrivalPlace;
  }

  DateTime arrivalDateTime() {
    return arrivalDateTime;
  }

  Capacity availableCapacity() {
    return availableCapacity;
  }

  Money price() {
    return price;
  }

  FindFlightDTO toFindFlightDTO() {
    return new FindFlightDTO(id.id(), departureDateTime.value(), price.amount());
  }

  SearchFlightDTO toSearchFlightDTO() {
    return new SearchFlightDTO(
        id.id(),
        code.code(),
        departurePlace.name(),
        departureDateTime.value(),
        arrivalPlace.name(),
        arrivalDateTime.value(),
        price.amount());
  }
}
