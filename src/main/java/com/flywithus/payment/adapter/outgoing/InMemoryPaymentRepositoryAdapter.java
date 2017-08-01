package com.flywithus.payment.adapter.outgoing;

import com.flywithus.payment.dto.PaymentDTO;
import com.flywithus.payment.port.outgoing.PaymentRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.text.MessageFormat.format;

@Repository
public class InMemoryPaymentRepositoryAdapter implements PaymentRepositoryPort {

    private ConcurrentHashMap<String, PaymentDTO> storage = new ConcurrentHashMap<>();

    @Override
    public void save(PaymentDTO payment) {
        storage.put(payment.getId(), payment);
    }

    @Override
    public List<PaymentDTO> findAllExpirablePayments() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public PaymentDTO find(String id) {
        return Optional.ofNullable(storage.get(id))
                .orElseThrow(() -> new IllegalStateException(format("No payment with id {0} found", id)));
    }

    public void clear() {
        storage.clear();
    }

}
