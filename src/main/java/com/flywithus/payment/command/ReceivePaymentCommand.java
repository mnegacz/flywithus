package com.flywithus.payment.command;

public class ReceivePaymentCommand {

  private String paymentId;

  public ReceivePaymentCommand() {}

  public ReceivePaymentCommand(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }
}
