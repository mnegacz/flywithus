package com.flywithus.user.domain;

import com.flywithus.user.command.RegisterUserCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserApplicationServiceTest {

    private static final Username USERNAME = Username.of("username");
    private static final Password PASSWORD = Password.of("password".toCharArray());

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserFactory userFactory;

    @Mock
    private User user;

    @InjectMocks
    private UserApplicationService testee;

    @Test
    public void shouldRegisterUser() {
        // given
        RegisterUserCommand command = new RegisterUserCommand(USERNAME.username(), PASSWORD.password());

        given(userFactory.createUser(USERNAME, PASSWORD)).willReturn(user);

        // when
        testee.register(command);

        // then
        verify(userRepository).save(user);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenRegisterUserCommandIsNull() {
        // given
        RegisterUserCommand command = null;

        // when
        Throwable result = catchThrowable(() -> testee.register(command));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}
