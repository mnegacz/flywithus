package com.flywithus.payment.adapter.outgoing;

import com.flywithus.payment.port.outgoing.PaymentOperatorPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.google.common.collect.Iterables.getOnlyElement;

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
