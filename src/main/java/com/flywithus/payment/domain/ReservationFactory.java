package com.flywithus.payment.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class ReservationFactory {

    Reservation createReservation(ReservationId id, Money amount, DateTime dateTime) {
        assertNotNull(id, "id");
        assertNotNull(amount, "amount");
        assertNotNull(dateTime, "dateTime");

        return new Reservation(id, amount, dateTime);
    }

}
