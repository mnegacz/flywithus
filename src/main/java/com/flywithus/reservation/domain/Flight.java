package com.flywithus.reservation.domain;

import com.flywithus.reservation.dto.FlightDTO;

class Flight {

    private final FlightId id;
    private final DateTime departureDateTime;
    private final Money price;

    Flight(FlightId id, DateTime departureDateTime, Money price) {
        this.id = id;
        this.departureDateTime = departureDateTime;
        this.price = price;
    }

    FlightId id() {
        return id;
    }

    DateTime departureDateTime() {
        return departureDateTime;
    }

    Money price() {
        return price;
    }

    FlightDTO toDTO() {
        return new FlightDTO(id.id(), departureDateTime.value(), price.amount());
    }

}
