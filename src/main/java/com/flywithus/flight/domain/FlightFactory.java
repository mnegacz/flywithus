package com.flywithus.flight.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class FlightFactory {

    Flight createFlight(FlightId id, Code code, Place departurePlace, DateTime departureDateTime, Place arrivalPlace, DateTime arrivalDateTime, Capacity availableCapacity, Money price) {
        assertNotNull(id, "id");
        assertNotNull(code, "code");
        assertNotNull(departurePlace, "departurePlace");
        assertNotNull(departureDateTime, "departureDateTime");
        assertNotNull(arrivalPlace, "arrivalPlace");
        assertNotNull(arrivalDateTime, "arrivalDateTime");
        assertNotNull(availableCapacity, "availableCapacity");
        assertNotNull(price, "price");

        return new Flight(id, code, departurePlace, departureDateTime, arrivalPlace, arrivalDateTime, availableCapacity, price);
    }

}
