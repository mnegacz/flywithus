package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import lombok.val;
import org.junit.Test;

public class PasswordTest {

  @Test
  public void shouldReturnPassword() {
    // given
    val password = "password".toCharArray();

    // when
    val result = Password.of(password);

    // then
    assertThat(result).isNotNull();
    assertThat(result.value()).isEqualTo(password);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenPasswordIsNull() {
    // when
    val result = catchThrowable(() -> Password.of(null));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
