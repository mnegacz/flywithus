package com.flywithus.flight.port.incoming;

import com.flywithus.flight.command.SearchFlightCommand;
import com.flywithus.flight.dto.SearchFlightDTO;
import java.util.List;

public interface SearchFlightPort {

  List<SearchFlightDTO> search(SearchFlightCommand command);
}
