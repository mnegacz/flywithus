package com.flywithus.payment.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class ReservationId {

    private final String id;

    private ReservationId(String id) {
        this.id = id;
    }

    static ReservationId of(String id) {
        assertNotNull(id, "id");

        return new ReservationId(id);
    }

    String id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationId that = (ReservationId) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "ReservationId{" +
                "id='" + id + '\'' +
                '}';
    }

}
