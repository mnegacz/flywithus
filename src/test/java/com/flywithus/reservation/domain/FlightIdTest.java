package com.flywithus.reservation.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FlightIdTest {

    @Test
    public void shouldReturnFlightId() {
        // given
        String id = "flight id";

        // when
        FlightId result = FlightId.of(id);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(id);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        // given
        String id = null;

        // when
        Throwable result = catchThrowable(() -> FlightId.of(id));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}