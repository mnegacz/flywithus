package com.flywithus.reservation.port.incoming;

import com.flywithus.reservation.command.ChangeReservationCommand;

public interface ChangeReservationPort {

    void change(ChangeReservationCommand command);

}
