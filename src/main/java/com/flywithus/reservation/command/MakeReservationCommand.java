package com.flywithus.reservation.command;

public class MakeReservationCommand {

    private String flightId;
    private int numberOfPeople;
    private String userId;

    public MakeReservationCommand() {
    }

    public MakeReservationCommand(String flightId, int numberOfPeople) {
        this.flightId = flightId;
        this.numberOfPeople = numberOfPeople;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
