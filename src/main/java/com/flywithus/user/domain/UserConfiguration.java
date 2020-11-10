package com.flywithus.user.domain;

import com.flywithus.user.port.outgoing.UserRepositoryPort;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

  @Bean
  UserApplicationService userApplicationService(UserRepositoryPort userRepositoryPort) {
    val userFactory = new UserFactory();
    val userRepository = new UserRepository(userRepositoryPort);
    return new UserApplicationService(userFactory, userRepository);
  }
}
