package com.flywithus.payment.command;

public class RegisterPaymentCommand {

  private String reservationId;

  public RegisterPaymentCommand() {}

  public RegisterPaymentCommand(String reservationId) {
    this.reservationId = reservationId;
  }

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }
}
