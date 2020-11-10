package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class UserId {

  private final String id;

  private UserId(String id) {
    this.id = id;
  }

  String id() {
    return id;
  }

  static UserId of(String id) {
    assertNotNull(id, "id");

    return new UserId(id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserId userId = (UserId) o;

    return id.equals(userId.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
