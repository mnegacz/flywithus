package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import java.util.Optional;

class ClientRepository {

  private final ClientFactory factory;

  ClientRepository(ClientFactory factory) {
    this.factory = factory;
  }

  Client find(Optional<UserId> clientId) {
    assertNotNull(clientId, "clientId");

    return clientId.map(factory::createRegisteredUser).orElseGet(factory::createUnregisteredUser);
  }
}
