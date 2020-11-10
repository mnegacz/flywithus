package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import lombok.val;
import org.junit.Test;

public class UserIdTest {

  @Test
  public void returnsUserId() {
    val id = "user id";

    val result = UserId.of(id);

    assertThat(result).isEqualTo(new UserId(id));
  }

  @Test
  public void generatesNewUserId() {
    val result = UserId.generate();

    assertThat(result).isNotNull();
  }

  @Test
  public void throwsIllegalArgumentExceptionWhenIdIsNull() {
    val result = catchThrowable(() -> UserId.of(null));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
