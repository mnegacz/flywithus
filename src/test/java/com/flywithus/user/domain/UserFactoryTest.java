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
  public void createsUser() {
    val result = testee.createUser(USERNAME, PASSWORD);

    assertThat(result).isNotNull();
    assertThat(result.id()).isNotNull();
    assertThat(result.username()).isEqualTo(USERNAME);
    assertThat(result.password()).isEqualTo(PASSWORD);
  }

  @Test
  public void throwsIllegalArgumentExceptionWhenUsernameIsNull() {
    val result = catchThrowable(() -> testee.createUser(null, PASSWORD));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void throwsIllegalArgumentExceptionWhenPasswordIsNull() {
    val result = catchThrowable(() -> testee.createUser(USERNAME, null));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
