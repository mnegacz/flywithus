package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import java.util.UUID;
import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Value
class UserId {

  String id;

  static UserId generate() {
    return of(UUID.randomUUID().toString());
  }

  static UserId of(String id) {
    assertNotNull(id, "id");

    return new UserId(id);
  }
}
