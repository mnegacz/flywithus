package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import lombok.val;
import org.junit.Test;

public class PasswordTest {

  @Test
  public void returnsPassword() {
    val password = "password".toCharArray();

    val result = Password.of(password);

    assertThat(result).isEqualTo(new Password(password));
  }

  @Test
  public void throwsIllegalArgumentExceptionWhenPasswordIsNull() {
    val result = catchThrowable(() -> Password.of(null));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
