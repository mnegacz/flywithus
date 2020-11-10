package com.flywithus.flight.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class Place {

  private final String name;

  private Place(String name) {
    this.name = name;
  }

  String name() {
    return name;
  }

  static Place of(String name) {
    assertNotNull(name, "name");

    return new Place(name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Place place = (Place) o;

    return name.equals(place.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
