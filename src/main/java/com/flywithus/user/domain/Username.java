package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Value
class Username {

  String value;

  static Username of(String value) {
    assertNotNull(value, "value");

    return new Username(value);
  }
}
