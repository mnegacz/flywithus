package com.flywithus.reservation.port.outgoing;

import com.flywithus.reservation.dto.ReservationDTO;

public interface ReservationRepositoryPort {

  void save(ReservationDTO reservation);

  ReservationDTO find(String id);
}
