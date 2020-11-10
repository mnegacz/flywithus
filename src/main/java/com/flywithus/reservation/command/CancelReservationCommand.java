package com.flywithus.reservation.command;

public class CancelReservationCommand {

  private String reservationId;

  public CancelReservationCommand() {}

  public CancelReservationCommand(String reservationId) {
    this.reservationId = reservationId;
  }

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }
}
