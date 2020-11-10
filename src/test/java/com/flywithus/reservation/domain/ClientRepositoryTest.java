package com.flywithus.reservation.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientRepositoryTest {

  @Mock private UnregisteredUser unregisteredUser;

  @Mock private RegisteredUser registeredUser;

  @Mock private ClientFactory factory;

  @Mock private UserId id;

  @InjectMocks private ClientRepository testee;

  @Before
  public void setUp() {
    given(factory.createUnregisteredUser()).willReturn(unregisteredUser);
    given(factory.createRegisteredUser(any())).willReturn(registeredUser);
  }

  @Test
  public void shouldFindUnregisteredUserWhenUserIdIsNotProvided() {
    // given
    Optional<UserId> userId = Optional.empty();

    // when
    Client result = testee.find(userId);

    // then
    assertThat(result).isInstanceOf(UnregisteredUser.class);
  }

  @Test
  public void shouldFindRegisteredUserWhenUserIdIsProvided() {
    // given
    Optional<UserId> userId = Optional.of(id);

    // when
    Client result = testee.find(userId);

    // then
    assertThat(result).isInstanceOf(RegisteredUser.class);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenUserIdIsNull() {
    // given
    Optional<UserId> userId = null;

    // when
    Throwable result = catchThrowable(() -> testee.find(userId));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
