package com.flywithus.flight.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CodeTest {

    @Test
    public void shouldReturnCode() {
        // given
        String code = "code";

        // when
        Code result = Code.of(code);

        // then
        assertThat(result).isNotNull();
        assertThat(result.code()).isEqualTo(code);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCodeIsNull() {
        // given
        String code = null;

        // when
        Throwable result = catchThrowable(() -> Code.of(code));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }
    
}