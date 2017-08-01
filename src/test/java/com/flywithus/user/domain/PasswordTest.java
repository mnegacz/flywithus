package com.flywithus.user.domain;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PasswordTest {

    @Test
    public void shouldReturnPassword() {
        // given
        char[] password = "password".toCharArray();

        // when
        Password result = Password.of(password);

        // then
        assertThat(result).isNotNull();
        assertThat(result.password()).isEqualTo(password);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenPasswordIsNull() {
        // given
        char[] password = null;

        // when
        Throwable result = catchThrowable(() -> Password.of(password));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}
