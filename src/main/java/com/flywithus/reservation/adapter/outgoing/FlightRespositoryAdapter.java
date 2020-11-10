package com.flywithus.reservation.adapter.outgoing;

import com.flywithus.flight.command.FindFlightCommand;
import com.flywithus.flight.dto.FindFlightDTO;
import com.flywithus.flight.port.incoming.FindFlightPort;
import com.flywithus.reservation.dto.FlightDTO;
import com.flywithus.reservation.port.outgoing.FlightRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class FlightRespositoryAdapter implements FlightRepositoryPort {

  private final FindFlightPort findFlightPort;

  public FlightRespositoryAdapter(FindFlightPort findFlightPort) {
    this.findFlightPort = findFlightPort;
  }

  @Override
  public FlightDTO find(String id) {
    FindFlightDTO dto = findFlightPort.find(new FindFlightCommand(id));
    return new FlightDTO(dto.getId(), dto.getDepartureDateTime(), dto.getPrice());
  }
}
