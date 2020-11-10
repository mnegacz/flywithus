package com.flywithus.infrastructure.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.flywithus.user.dto.UserDto;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

@RunWith(MockitoJUnitRunner.class)
public class SecurityContextFacadeTest {

  private static final String USER_ID = "user id";

  @Mock private SecurityContext securityContext;

  @Mock private Authentication authentication;

  @Spy private SecurityContextFacade testee;

  @Before
  public void setUp() {
    given(testee.context()).willReturn(securityContext);
  }

  @Test
  public void shouldReturnUserId() {
    // given
    val userDTO = UserDto.builder().id(USER_ID).build();
    given(securityContext.getAuthentication()).willReturn(authentication);
    given(authentication.getPrincipal()).willReturn(userDTO);

    // when
    String result = testee.findCurrentUserId();

    // then
    assertThat(result).isEqualTo(USER_ID);
  }

  @Test
  public void shouldReturnNullWhenUserIsNotLoggedIn() {
    // when
    String result = testee.findCurrentUserId();

    // then
    assertThat(result).isNull();
  }
}
