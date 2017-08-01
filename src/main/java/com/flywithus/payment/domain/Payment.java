package com.flywithus.payment.domain;

import com.flywithus.payment.dto.PaymentDTO;
import com.flywithus.payment.exception.CannotExpirePaymentException;
import com.flywithus.payment.exception.CannotReceivePaymentException;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static com.flywithus.payment.domain.PaymentStatus.*;

class Payment {

    private static final int TWO_DAYS = 2;

    private PaymentId id;
    private Reservation reservation;
    private PaymentStatus status;

    Payment(PaymentId id, Reservation reservation, PaymentStatus status) {
        this.id = id;
        this.reservation = reservation;
        this.status = status;
    }

    boolean canBeReceived() {
        return isNotReceived() && isNotExpired();
    }

    void receive() {
        if (!canBeReceived()) {
            throw new CannotReceivePaymentException();
        }
        status = RECEIVED;
    }

    boolean canBeExpired(DateTime now) {
        return isNotReceived() && isNotExpired() && twoDaysHavePassed(now);
    }

    private boolean isNotReceived() {
        return !RECEIVED.equals(status);
    }

    private boolean isNotExpired() {
        return !EXPIRED.equals(status);
    }

    private boolean twoDaysHavePassed(DateTime now) {
        assertNotNull(now, "now");

        DateTime reservationDateTime = reservation().dateTime();
        int differenceInDays = now.differenceInDaysBetween(reservationDateTime);
        return differenceInDays >= TWO_DAYS;
    }

    void expire(DateTime now) {
        if (!canBeExpired(now)) {
            throw new CannotExpirePaymentException();
        }
        status = EXPIRED;
    }

    PaymentId id() {
        return id;
    }

    Reservation reservation() {
        return reservation;
    }

    PaymentStatus status() {
        return status;
    }

    PaymentDTO toDTO() {
        return new PaymentDTO(id().id(), status.toDTO(), reservation.toDTO());
    }

}
