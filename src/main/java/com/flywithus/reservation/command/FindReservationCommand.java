package com.flywithus.reservation.command;

public class FindReservationCommand {

    private String id;

    public FindReservationCommand() {
    }

    public FindReservationCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
