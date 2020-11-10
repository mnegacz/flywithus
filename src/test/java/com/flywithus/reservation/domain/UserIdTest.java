package com.flywithus.reservation.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;

public class UserIdTest {

  @Test
  public void shouldReturnUserId() {
    // given
    String userId = "user id";

    // when
    UserId result = UserId.of(userId);

    // then
    assertThat(result).isNotNull();
    assertThat(result.id()).isEqualTo(userId);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenUserIdIsNull() {
    // given
    String userId = null;

    // when
    Throwable result = catchThrowable(() -> UserId.of(userId));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
