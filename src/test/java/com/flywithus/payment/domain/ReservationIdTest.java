package com.flywithus.payment.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ReservationIdTest {

    @Test
    public void shouldReturnReservationId() {
        // given
        String id = "reservation id";

        // when
        ReservationId result = ReservationId.of(id);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(id);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        // given
        String id = null;

        // when
        Throwable result = catchThrowable(() -> ReservationId.of(id));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}