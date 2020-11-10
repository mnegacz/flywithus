package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import java.util.Arrays;

class Password {

  private final char[] password;

  private Password(char[] password) {
    this.password = password;
  }

  static Password of(char[] password) {
    assertNotNull(password, "password");

    return new Password(password);
  }

  char[] password() {
    return password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Password password1 = (Password) o;

    return Arrays.equals(password, password1.password);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(password);
  }
}
