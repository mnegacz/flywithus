package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import lombok.val;
import org.junit.Test;

public class UsernameTest {

  @Test
  public void shouldReturnUsername() {
    // given
    val username = "username";

    // when
    val result = Username.of(username);

    // then
    assertThat(result).isNotNull();
    assertThat(result.value()).isEqualTo(username);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenUsernameIsNull() {
    // when
    val result = catchThrowable(() -> Username.of(null));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
