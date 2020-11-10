package com.flywithus.infrastructure.security;

import com.flywithus.user.adapter.outgoing.InMemoryUserRepositoryAdapter;
import com.flywithus.user.dto.UserDTO;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

  private static final String ROLE_CLIENT = "ROLE_CLIENT";

  private final InMemoryUserRepositoryAdapter inMemoryUserRepositoryAdapter;

  public UserAuthenticationProvider(InMemoryUserRepositoryAdapter inMemoryUserRepositoryAdapter) {
    this.inMemoryUserRepositoryAdapter = inMemoryUserRepositoryAdapter;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    try {
      UserDTO userDTO = inMemoryUserRepositoryAdapter.findByUsername(authentication.getName());
      if (passwordMatches(authentication, userDTO)) {
        return createToken(authentication, userDTO);
      } else {
        throw new BadCredentialsException(authentication.getName());
      }
    } catch (IllegalStateException e) {
      throw new UsernameNotFoundException(authentication.getName(), e);
    }
  }

  private UsernamePasswordAuthenticationToken createToken(
      Authentication authentication, UserDTO userDTO) {
    List<SimpleGrantedAuthority> authorities =
        ImmutableList.of(new SimpleGrantedAuthority(ROLE_CLIENT));
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(
            userDTO, authentication.getCredentials(), authorities);
    token.setDetails(authentication.getDetails());
    return token;
  }

  private boolean passwordMatches(Authentication authentication, UserDTO userDTO) {
    return Arrays.equals(
        authentication.getCredentials().toString().toCharArray(), userDTO.getPassword());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
