package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import lombok.val;
import org.junit.Test;

public class UsernameTest {

  @Test
  public void returnsUsername() {
    val username = "username";

    val result = Username.of(username);

    assertThat(result).isNotNull();
    assertThat(result.value()).isEqualTo(username);
  }

  @Test
  public void throwsIllegalArgumentExceptionWhenUsernameIsNull() {
    val result = catchThrowable(() -> Username.of(null));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
