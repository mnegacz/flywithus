package com.flywithus.reservation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlightDTO {

    private final String id;
    private final LocalDateTime departureDateTime;
    private final BigDecimal price;

    public FlightDTO(String id, LocalDateTime departureDateTime, BigDecimal price) {
        this.id = id;
        this.departureDateTime = departureDateTime;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
