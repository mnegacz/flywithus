package com.flywithus.reservation.port.incoming;

import com.flywithus.reservation.command.MakeReservationCommand;

public interface MakeReservationPort {

    void make(MakeReservationCommand command);

}
