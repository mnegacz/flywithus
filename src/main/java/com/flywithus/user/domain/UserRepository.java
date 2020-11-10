package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import com.flywithus.user.port.outgoing.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UserRepository {

  private final UserRepositoryPort userRepositoryPort;

  void save(User user) {
    assertNotNull(user, "user");

    userRepositoryPort.save(user.toDTO());
  }
}
