package com.flywithus.flight.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static java.util.stream.Collectors.toList;

import com.flywithus.flight.command.FindFlightCommand;
import com.flywithus.flight.command.SearchFlightCommand;
import com.flywithus.flight.dto.FindFlightDTO;
import com.flywithus.flight.dto.SearchFlightCriteria;
import com.flywithus.flight.dto.SearchFlightDTO;
import com.flywithus.flight.port.incoming.FindFlightPort;
import com.flywithus.flight.port.incoming.SearchFlightPort;
import java.util.List;

public class FlightApplicationService implements SearchFlightPort, FindFlightPort {

  private final FlightRepository flightRepository;

  FlightApplicationService(FlightRepository repository) {
    this.flightRepository = repository;
  }

  @Override
  public List<SearchFlightDTO> search(SearchFlightCommand command) {
    assertNotNull(command, "command");

    SearchFlightCriteria criteria = createSearchCriteria(command);
    return flightRepository.findAll(criteria).stream()
        .map(Flight::toSearchFlightDTO)
        .collect(toList());
  }

  private SearchFlightCriteria createSearchCriteria(SearchFlightCommand command) {
    return new SearchFlightCriteria(
        command.getDeparturePlace(),
        command.getDepartureDate(),
        command.getArrivalPlace(),
        command.getArrivalDate(),
        command.getNumberOfPeople());
  }

  @Override
  public FindFlightDTO find(FindFlightCommand command) {
    assertNotNull(command, "command");

    return flightRepository.find(FlightId.of(command.getId())).toFindFlightDTO();
  }
}
