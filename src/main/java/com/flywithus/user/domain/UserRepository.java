package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import com.flywithus.user.port.outgoing.UserRepositoryPort;

class UserRepository {

  private final UserRepositoryPort userRepositoryPort;

  UserRepository(UserRepositoryPort userRepositoryPort) {
    this.userRepositoryPort = userRepositoryPort;
  }

  void save(User user) {
    assertNotNull(user, "user");

    userRepositoryPort.save(user.toDTO());
  }
}
