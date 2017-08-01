package com.flywithus.reservation.port.incoming;

import com.flywithus.reservation.command.FindReservationCommand;
import com.flywithus.reservation.dto.FindReservationDTO;

public interface FindReservationPort {

    FindReservationDTO find(FindReservationCommand command);

}
