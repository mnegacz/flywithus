package com.flywithus.reservation.domain;

import com.flywithus.reservation.dto.ReservationStatusDTO;

enum ReservationStatus {
    CREATED, CANCELLED;

    ReservationStatusDTO toDTO() {
        return ReservationStatusDTO.valueOf(this.name());
    }

}
