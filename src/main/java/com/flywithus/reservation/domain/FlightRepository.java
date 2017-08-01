package com.flywithus.reservation.domain;

import com.flywithus.reservation.dto.FlightDTO;
import com.flywithus.reservation.port.outgoing.FlightRepositoryPort;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class FlightRepository {

    private final FlightRepositoryPort flightRepositoryPort;

    FlightRepository(FlightRepositoryPort flightRepositoryPort) {
        this.flightRepositoryPort = flightRepositoryPort;
    }

    Flight find(FlightId id) {
        assertNotNull(id, "id");

        FlightDTO dto = flightRepositoryPort.find(id.id());
        DateTime departureDateTime = DateTime.of(dto.getDepartureDateTime());
        Money price = Money.of(dto.getPrice());

        return new Flight(id, departureDateTime, price);
    }

}
