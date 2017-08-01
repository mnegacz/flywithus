package com.flywithus.payment.port.outgoing;

import com.flywithus.payment.dto.ReservationDTO;

public interface ReservationRepositoryPort {

    ReservationDTO find(String id);

}
