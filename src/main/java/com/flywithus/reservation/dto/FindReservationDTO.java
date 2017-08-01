package com.flywithus.reservation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FindReservationDTO {

    private final String id;
    private final BigDecimal amount;
    private final LocalDateTime dateTime;

    public FindReservationDTO(String id, BigDecimal amount, LocalDateTime dateTime) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
