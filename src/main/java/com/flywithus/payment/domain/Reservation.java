package com.flywithus.payment.domain;

import com.flywithus.payment.dto.ReservationDTO;

class Reservation {

    private ReservationId id;
    private Money amount;
    private DateTime dateTime;

    Reservation(ReservationId id, Money amount, DateTime dateTime) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    ReservationId id() {
        return id;
    }

    Money amount() {
        return amount;
    }

    DateTime dateTime() {
        return dateTime;
    }

    ReservationDTO toDTO() {
        return new ReservationDTO(id.id(), amount.amount(), dateTime.value());
    }

}
