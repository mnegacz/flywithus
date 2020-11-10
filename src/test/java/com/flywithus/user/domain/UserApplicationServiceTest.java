package com.flywithus.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.flywithus.user.command.RegisterUserCommand;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserApplicationServiceTest {

  private static final Username USERNAME = Username.of("username");
  private static final Password PASSWORD = Password.of("password".toCharArray());

  @Mock private UserRepository userRepository;

  @Mock private UserFactory userFactory;

  @Mock private User user;

  @InjectMocks private UserApplicationService testee;

  @Test
  public void registersUser() {
    val command = new RegisterUserCommand(USERNAME.value(), PASSWORD.value());
    given(userFactory.createUser(USERNAME, PASSWORD)).willReturn(user);

    testee.register(command);

    verify(userRepository).save(user);
  }

  @Test
  public void throwsIllegalArgumentExceptionWhenRegisterUserCommandIsNull() {
    val result = catchThrowable(() -> testee.register(null));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
