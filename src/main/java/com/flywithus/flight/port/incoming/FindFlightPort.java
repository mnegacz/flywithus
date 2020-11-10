package com.flywithus.flight.port.incoming;

import com.flywithus.flight.command.FindFlightCommand;
import com.flywithus.flight.dto.FindFlightDTO;

public interface FindFlightPort {

  FindFlightDTO find(FindFlightCommand command);
}
