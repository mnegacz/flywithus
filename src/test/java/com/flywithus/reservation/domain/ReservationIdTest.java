package com.flywithus.reservation.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ReservationIdTest {

    @Test
    public void shouldReturnReservationId() {
        // given
        String reservationId = "reservation id";

        // when
        ReservationId result = ReservationId.of(reservationId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(reservationId);
    }

    @Test
    public void shouldReturnGeneratedReservationId() {
        // when
        ReservationId result = ReservationId.generate();

        // then
        assertThat(result).isNotNull();
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        // given
        String reservationId = null;

        // when
        Throwable result = catchThrowable(() -> ReservationId.of(reservationId));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}