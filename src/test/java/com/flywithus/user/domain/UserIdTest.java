package com.flywithus.user.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class UserIdTest {

    @Test
    public void shouldReturnUserId() {
        // given
        String id = "user id";

        // when
        UserId result = UserId.of(id);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(id);
    }

    @Test
    public void shouldGenerateNewUserId() {
        // when
        UserId result = UserId.generate();

        // then
        assertThat(result).isNotNull();
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        // given
        String id = null;

        // when
        Throwable result = catchThrowable(() -> UserId.of(id));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}
