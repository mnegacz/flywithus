package com.flywithus.reservation.command;

public class ChangeReservationCommand {

    private String reservationId;
    private String flightId;
    private int numberOfPeople;

    public ChangeReservationCommand() {
    }

    public ChangeReservationCommand(String reservationId, String flightId, int numberOfPeople) {
        this.reservationId = reservationId;
        this.flightId = flightId;
        this.numberOfPeople = numberOfPeople;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

}
