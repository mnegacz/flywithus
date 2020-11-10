package com.flywithus.payment.adapter.outgoing;

import com.flywithus.payment.dto.ReservationDTO;
import com.flywithus.payment.port.outgoing.ReservationRepositoryPort;
import com.flywithus.reservation.command.FindReservationCommand;
import com.flywithus.reservation.dto.FindReservationDTO;
import com.flywithus.reservation.port.incoming.FindReservationPort;
import org.springframework.stereotype.Repository;

@Repository
class ReservationRepositoryAdapter implements ReservationRepositoryPort {

  private final FindReservationPort findReservationPort;

  public ReservationRepositoryAdapter(FindReservationPort findReservationPort) {
    this.findReservationPort = findReservationPort;
  }

  @Override
  public ReservationDTO find(String id) {
    FindReservationDTO dto = findReservationPort.find(new FindReservationCommand(id));
    return new ReservationDTO(dto.getId(), dto.getAmount(), dto.getDateTime());
  }
}
