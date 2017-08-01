package com.flywithus.reservation.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class ClientFactoryTest {

    @Mock
    private UserId id;

    private ClientFactory testee = new ClientFactory();

    @Test
    public void shouldCreateNonRegisteredUser() {
        // when
        Client result = testee.createUnregisteredUser();

        // then
        assertThat(result).isInstanceOf(UnregisteredUser.class);
    }

    @Test
    public void shouldCreateRegisteredUser() {
        // when
        Client result = testee.createRegisteredUser(id);

        // then
        assertThat(result).isInstanceOf(RegisteredUser.class);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIdIsNull() {
        // given
        UserId id = null;

        // when
        Throwable result = catchThrowable(() -> testee.createRegisteredUser(id));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}