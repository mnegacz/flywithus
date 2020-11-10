package com.flywithus.payment.dto;

public class PaymentDTO {

  private final String id;
  private final PaymentStatusDTO status;
  private final ReservationDTO reservationDTO;

  public PaymentDTO(String id, PaymentStatusDTO status, ReservationDTO reservationDTO) {
    this.id = id;
    this.status = status;
    this.reservationDTO = reservationDTO;
  }

  public String getId() {
    return id;
  }

  public PaymentStatusDTO getStatus() {
    return status;
  }

  public ReservationDTO getReservationDTO() {
    return reservationDTO;
  }
}
