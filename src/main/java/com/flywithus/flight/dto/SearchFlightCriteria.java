package com.flywithus.flight.dto;

import java.time.LocalDate;
import java.util.Optional;

public class SearchFlightCriteria {

    private final String departurePlace;
    private final LocalDate departureDate;
    private final String arrivalPlace;
    private final LocalDate arrivalDate;
    private final Integer numberOfPeople;

    public SearchFlightCriteria(String departurePlace, LocalDate departureDate, String arrivalPlace, LocalDate arrivalDate, Integer numberOfPeople) {
        this.departurePlace = departurePlace;
        this.departureDate = departureDate;
        this.arrivalPlace = arrivalPlace;
        this.arrivalDate = arrivalDate;
        this.numberOfPeople = numberOfPeople;
    }

    public Optional<String> getDeparturePlace() {
        return Optional.ofNullable(departurePlace);
    }

    public Optional<LocalDate> getDepartureDate() {
        return Optional.ofNullable(departureDate);
    }

    public Optional<String> getArrivalPlace() {
        return Optional.ofNullable(arrivalPlace);
    }

    public Optional<LocalDate> getArrivalDate() {
        return Optional.ofNullable(arrivalDate);
    }

    public Optional<Integer> getNumberOfPeople() {
        return Optional.ofNullable(numberOfPeople);
    }

}
