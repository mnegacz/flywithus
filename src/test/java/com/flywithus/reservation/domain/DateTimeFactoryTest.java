package com.flywithus.reservation.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeFactoryTest {

    private DateTimeFactory testee = new DateTimeFactory();

    @Test
    public void shouldReturnDateTime() {
        // when
        DateTime result = testee.now();

        // then
        assertThat(result).isNotNull();
    }

}
