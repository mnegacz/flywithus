package com.flywithus.payment.port.outgoing;

import java.math.BigDecimal;

public interface PaymentOperatorPort {

  String requestPayment(BigDecimal amount);

  void expirePayment(String id);
}
