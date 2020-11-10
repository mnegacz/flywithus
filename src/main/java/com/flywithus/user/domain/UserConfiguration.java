package com.flywithus.user.domain;

import com.flywithus.user.port.outgoing.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

  @Bean
  UserApplicationService userApplicationService(UserRepositoryPort userRepositoryPort) {
    UserFactory userFactory = new UserFactory();
    UserRepository userRepository = new UserRepository(userRepositoryPort);
    return new UserApplicationService(userFactory, userRepository);
  }
}
