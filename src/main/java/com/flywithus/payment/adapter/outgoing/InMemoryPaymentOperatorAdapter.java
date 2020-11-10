package com.flywithus.payment.adapter.outgoing;

import static com.google.common.collect.Iterables.getOnlyElement;

import com.flywithus.payment.port.outgoing.PaymentOperatorPort;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Component;

@Component
public class InMemoryPaymentOperatorAdapter implements PaymentOperatorPort {

  private CopyOnWriteArrayList<String> storage = new CopyOnWriteArrayList<>();

  @Override
  public String requestPayment(BigDecimal amount) {
    String id = UUID.randomUUID().toString();
    storage.add(id);
    return id;
  }

  @Override
  public void expirePayment(String id) {
    storage.remove(id);
  }

  public String only() {
    return getOnlyElement(storage);
  }

  public void clear() {
    storage.clear();
  }
}
