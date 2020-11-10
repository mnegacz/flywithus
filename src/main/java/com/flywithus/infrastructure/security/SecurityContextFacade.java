package com.flywithus.infrastructure.security;

import com.flywithus.user.dto.UserDto;
import java.util.Optional;
import javax.annotation.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextFacade {

  @Nullable
  public String findCurrentUserId() {
    return Optional.ofNullable(context())
        .map(SecurityContext::getAuthentication)
        .map(Authentication::getPrincipal)
        .filter(UserDto.class::isInstance)
        .map(UserDto.class::cast)
        .map(UserDto::getId)
        .orElse(null);
  }

  SecurityContext context() {
    return SecurityContextHolder.getContext();
  }
}
