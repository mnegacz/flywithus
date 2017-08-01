package com.flywithus.flight.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlightDTO {

    private final String id;
    private final String code;
    private final String departurePlace;
    private final LocalDateTime departureDateTime;
    private final String arrivalPlace;
    private final LocalDateTime arrivalDateTime;
    private final Integer availableCapacity;
    private final BigDecimal price;

    public FlightDTO(String id, String code, String departurePlace, LocalDateTime departureDateTime, String arrivalPlace, LocalDateTime arrivalDateTime, Integer availableCapacity, BigDecimal price) {
        this.id = id;
        this.code = code;
        this.departurePlace = departurePlace;
        this.departureDateTime = departureDateTime;
        this.arrivalPlace = arrivalPlace;
        this.arrivalDateTime = arrivalDateTime;
        this.availableCapacity = availableCapacity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public Integer getAvailableCapacity() {
        return availableCapacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
