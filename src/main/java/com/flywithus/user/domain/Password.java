package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Value
class Password {

  char[] value;

  static Password of(char[] password) {
    assertNotNull(password, "password");

    return new Password(password);
  }
}
