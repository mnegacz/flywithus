package com.flywithus.infrastructure.security;

import com.flywithus.user.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Optional;

@Component
public class SecurityContextFacade {

    @Nullable
    public String findCurrentUserId() {
        return Optional.ofNullable(context())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(UserDTO.class::isInstance)
                .map(UserDTO.class::cast)
                .map(UserDTO::getId)
                .orElse(null);
    }

    SecurityContext context() {
        return SecurityContextHolder.getContext();
    }

}
