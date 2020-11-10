package com.flywithus.payment.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class PaymentId {

  private final String id;

  private PaymentId(String id) {
    this.id = id;
  }

  static PaymentId of(String id) {
    assertNotNull(id, "id");

    return new PaymentId(id);
  }

  String id() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PaymentId paymentId = (PaymentId) o;

    return id.equals(paymentId.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "PaymentId{" + "id='" + id + '\'' + '}';
  }
}
