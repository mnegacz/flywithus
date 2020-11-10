package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;

public class UsernameTest {

  @Test
  public void shouldReturnUsername() {
    // given
    String username = "username";

    // when
    Username result = Username.of(username);

    // then
    assertThat(result).isNotNull();
    assertThat(result.username()).isEqualTo(username);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenUsernameIsNull() {
    // given
    String username = null;

    // when
    Throwable result = catchThrowable(() -> Username.of(username));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
