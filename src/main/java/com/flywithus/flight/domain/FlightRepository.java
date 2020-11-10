package com.flywithus.flight.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static java.util.stream.Collectors.toList;

import com.flywithus.flight.dto.FlightDTO;
import com.flywithus.flight.dto.SearchFlightCriteria;
import com.flywithus.flight.port.outgoing.FlightRepositoryPort;
import java.util.List;

class FlightRepository {

  private final FlightRepositoryPort flightRepositoryPort;
  private final FlightFactory flightFactory;

  FlightRepository(FlightRepositoryPort flightRepositoryPort, FlightFactory flightFactory) {
    this.flightRepositoryPort = flightRepositoryPort;
    this.flightFactory = flightFactory;
  }

  List<Flight> findAll(SearchFlightCriteria criteria) {
    assertNotNull(criteria, "criteria");

    return flightRepositoryPort.findAll(criteria).stream()
        .map(this::convertFlightDTOToFlight)
        .collect(toList());
  }

  Flight find(FlightId id) {
    FlightDTO dto = flightRepositoryPort.find(id.id());
    return convertFlightDTOToFlight(dto);
  }

  private Flight convertFlightDTOToFlight(FlightDTO dto) {
    FlightId id = FlightId.of(dto.getId());
    Code code = Code.of(dto.getCode());
    Place departurePlace = Place.of(dto.getDeparturePlace());
    DateTime departureDateTime = DateTime.of(dto.getDepartureDateTime());
    Place arrivalPlace = Place.of(dto.getArrivalPlace());
    DateTime arrivalDateTime = DateTime.of(dto.getArrivalDateTime());
    Capacity availableCapacity = Capacity.of(dto.getAvailableCapacity());
    Money price = Money.of(dto.getPrice());

    return flightFactory.createFlight(
        id,
        code,
        departurePlace,
        departureDateTime,
        arrivalPlace,
        arrivalDateTime,
        availableCapacity,
        price);
  }
}
