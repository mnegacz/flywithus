package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class ClientFactory {

  Client createRegisteredUser(UserId userId) {
    assertNotNull(userId, "userId");

    return new RegisteredUser(userId);
  }

  Client createUnregisteredUser() {
    return new UnregisteredUser();
  }
}
