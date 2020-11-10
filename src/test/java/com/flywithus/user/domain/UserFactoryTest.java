package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import lombok.val;
import org.junit.Test;

public class UserFactoryTest {

  private static final Username USERNAME = Username.of("username");
  private static final Password PASSWORD = Password.of("password".toCharArray());

  private UserFactory testee = new UserFactory();

  @Test
  public void shouldCreateUser() {
    // when
    val result = testee.createUser(USERNAME, PASSWORD);

    // then
    assertThat(result).isNotNull();
    assertThat(result.id()).isNotNull();
    assertThat(result.username()).isEqualTo(USERNAME);
    assertThat(result.password()).isEqualTo(PASSWORD);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenUsernameIsNull() {
    // when
    val result = catchThrowable(() -> testee.createUser(null, PASSWORD));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenPasswordIsNull() {
    // when
    val result = catchThrowable(() -> testee.createUser(USERNAME, null));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
