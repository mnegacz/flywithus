package com.flywithus.reservation.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private final String id;
    private final ClientDTO user;
    private final FlightDTO flight;
    private final int numberOfPeople;
    private final LocalDateTime dateTime;
    private final ReservationStatusDTO status;
    private DiscountDTO discount;

    public ReservationDTO(String id, ClientDTO user, FlightDTO flight, int numberOfPeople, LocalDateTime dateTime, ReservationStatusDTO status) {
        this.id = id;
        this.user = user;
        this.flight = flight;
        this.numberOfPeople = numberOfPeople;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public ClientDTO getUser() {
        return user;
    }

    public FlightDTO getFlight() {
        return flight;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public ReservationStatusDTO getStatus() {
        return status;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }

}
