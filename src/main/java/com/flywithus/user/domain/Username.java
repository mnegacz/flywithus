package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class Username {

  private final String username;

  private Username(String username) {
    this.username = username;
  }

  static Username of(String username) {
    assertNotNull(username, "username");

    return new Username(username);
  }

  String username() {
    return username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Username username1 = (Username) o;

    return username.equals(username1.username);
  }

  @Override
  public int hashCode() {
    return username.hashCode();
  }
}
