package com.flywithus.flight.port.outgoing;

import com.flywithus.flight.dto.FlightDTO;
import com.flywithus.flight.dto.SearchFlightCriteria;

import java.util.List;

public interface FlightRepositoryPort {

    List<FlightDTO> findAll(SearchFlightCriteria criteria);

    FlightDTO find(String id);

}
