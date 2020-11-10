package com.flywithus.payment.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static com.flywithus.payment.domain.PaymentStatus.CREATED;

class PaymentFactory {

  Payment createPayment(PaymentId id, Reservation reservation) {
    return createPayment(id, reservation, CREATED);
  }

  Payment createPayment(PaymentId id, Reservation reservation, PaymentStatus status) {
    assertNotNull(id, "id");
    assertNotNull(reservation, "reservation");
    assertNotNull(status, "status");

    return new Payment(id, reservation, status);
  }
}
