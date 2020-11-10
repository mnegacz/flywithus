package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;

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
