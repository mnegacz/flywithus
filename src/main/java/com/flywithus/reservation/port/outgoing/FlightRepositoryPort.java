package com.flywithus.reservation.port.outgoing;

import com.flywithus.reservation.dto.FlightDTO;

public interface FlightRepositoryPort {

  FlightDTO find(String id);
}
