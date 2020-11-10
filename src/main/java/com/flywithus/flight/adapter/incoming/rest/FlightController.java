package com.flywithus.flight.adapter.incoming.rest;

import com.flywithus.flight.command.SearchFlightCommand;
import com.flywithus.flight.dto.SearchFlightDTO;
import com.flywithus.flight.port.incoming.SearchFlightPort;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
class FlightController {

  private final SearchFlightPort searchFlightPort;

  FlightController(SearchFlightPort searchFlightPort) {
    this.searchFlightPort = searchFlightPort;
  }

  @PostMapping
  List<SearchFlightDTO> search(@RequestBody SearchFlightCommand command) {
    return searchFlightPort.search(command);
  }
}
