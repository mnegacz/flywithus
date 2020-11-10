package com.flywithus.infrastructure.security;

import com.flywithus.user.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SecurityContextFacadeTest {

    private static final String USER_ID = "user id";

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDTO userDTO;

    @Spy
    private SecurityContextFacade testee;

    @Before
    public void setUp() {
        given(testee.context()).willReturn(securityContext);
    }

    @Test
    public void shouldReturnUserId() {
        // given
        given(securityContext.getAuthentication()).willReturn(authentication);
        given(authentication.getPrincipal()).willReturn(userDTO);
        given(userDTO.getId()).willReturn(USER_ID);

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