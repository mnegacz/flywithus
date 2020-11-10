package com.flywithus.payment.domain;

import com.flywithus.payment.dto.ReservationDTO;
import com.flywithus.payment.port.outgoing.ReservationRepositoryPort;

class ReservationRepository {

  private final ReservationRepositoryPort reservationRepositoryPort;
  private final ReservationFactory reservationFactory;

  ReservationRepository(
      ReservationRepositoryPort reservationRepositoryPort, ReservationFactory reservationFactory) {
    this.reservationRepositoryPort = reservationRepositoryPort;
    this.reservationFactory = reservationFactory;
  }

  Reservation find(ReservationId reservationId) {
    ReservationDTO reservationDTO = reservationRepositoryPort.find(reservationId.id());
    Money amount = Money.of(reservationDTO.getAmount());
    DateTime dateTime = DateTime.of(reservationDTO.getDateTime());
    return reservationFactory.createReservation(reservationId, amount, dateTime);
  }
}
