package com.flywithus.reservation.dto;

import java.math.BigDecimal;

public class DiscountDTO {

  private final BigDecimal percent;

  public DiscountDTO(BigDecimal percent) {
    this.percent = percent;
  }

  public BigDecimal getPercent() {
    return percent;
  }
}
