package com.flywithus.infrastructure.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

import com.flywithus.user.adapter.outgoing.InMemoryUserRepositoryAdapter;
import com.flywithus.user.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserAuthenticationProviderTest {

  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";
  private static final String ROLE_CLIENT = "ROLE_CLIENT";

  @Mock private InMemoryUserRepositoryAdapter inMemoryUserRepositoryAdapter;

  @Mock private UserDTO userDTO;

  @Mock private Authentication authentication;

  @Mock private Object details;

  @InjectMocks private UserAuthenticationProvider testee;

  @Before
  public void setUp() {
    given(authentication.getName()).willReturn(USERNAME);
    given(authentication.getCredentials()).willReturn(PASSWORD);
    given(authentication.getDetails()).willReturn(details);
  }

  @Test
  public void shouldAuthenticateUser() {
    // given
    given(inMemoryUserRepositoryAdapter.findByUsername(USERNAME)).willReturn(userDTO);
    given(userDTO.getPassword()).willReturn(PASSWORD.toCharArray());

    // when
    Authentication result = testee.authenticate(authentication);

    // then
    assertThat(result).isInstanceOf(UsernamePasswordAuthenticationToken.class);
    assertThat(result.getPrincipal()).isEqualTo(userDTO);
    assertThat(result.getDetails()).isEqualTo(details);
    assertThat(result.getAuthorities()).extracting("authority").contains(ROLE_CLIENT);
  }

  @Test
  public void shouldThrowUsernameNotFoundExceptionWhenUserIsNotFound() {
    // given
    given(inMemoryUserRepositoryAdapter.findByUsername(USERNAME))
        .willThrow(IllegalStateException.class);

    // when
    Throwable result = catchThrowable(() -> testee.authenticate(authentication));

    // then
    assertThat(result).isInstanceOf(UsernameNotFoundException.class);
  }

  @Test
  public void shouldThrowBadCredentialsExceptionWhenCredentialsDoesNotMatch() {
    // given
    given(inMemoryUserRepositoryAdapter.findByUsername(USERNAME)).willReturn(userDTO);

    // when
    Throwable result = catchThrowable(() -> testee.authenticate(authentication));

    // then
    assertThat(result).isInstanceOf(BadCredentialsException.class);
  }
}
