package com.flywithus.reservation.port.incoming;

import com.flywithus.reservation.command.CancelReservationCommand;

public interface CancelReservationPort {

  void cancel(CancelReservationCommand command);
}
