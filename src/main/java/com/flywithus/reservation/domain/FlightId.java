package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class FlightId {

    private final String id;

    private FlightId(String id) {
        this.id = id;
    }

    String id() {
        return id;
    }

    static FlightId of(String id) {
        assertNotNull(id, "id");

        return new FlightId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightId flightId = (FlightId) o;

        return id.equals(flightId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "FlightId{" +
                "id='" + id + '\'' +
                '}';
    }

}
