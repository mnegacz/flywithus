package com.flywithus.flight.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CapacityTest {

    @Test
    public void shouldReturnCapacity() {
        // given
        int capacity = 1;

        // when
        Capacity result = Capacity.of(capacity);

        // then
        assertThat(result).isNotNull();
        assertThat(result.capacity()).isEqualTo(capacity);
    }

}
