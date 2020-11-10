package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserFactoryTest {

  @Mock private Username username;

  @Mock private Password password;

  private UserFactory testee = new UserFactory();

  @Test
  public void shouldCreateUser() {
    // when
    User result = testee.createUser(username, password);

    // then
    assertThat(result).isNotNull();
    assertThat(result.id()).isNotNull();
    assertThat(result.username()).isEqualTo(username);
    assertThat(result.password()).isEqualTo(password);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenUsernameIsNull() {
    // given
    Username anotherUsername = null;

    // when
    Throwable result = catchThrowable(() -> testee.createUser(anotherUsername, password));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenPasswordIsNull() {
    // given
    Password anotherPassword = null;

    // when
    Throwable result = catchThrowable(() -> testee.createUser(username, anotherPassword));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
