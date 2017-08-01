package com.flywithus.reservation.adapter.outgoing;

import com.flywithus.reservation.dto.ReservationDTO;
import com.flywithus.reservation.port.outgoing.ReservationRepositoryPort;
import com.google.common.collect.Iterables;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.text.MessageFormat.format;

@Repository
public class InMemoryReservationRepositoryAdapter implements ReservationRepositoryPort {

    private final ConcurrentHashMap<String, ReservationDTO> storage = new ConcurrentHashMap<>();

    @Override
    public void save(ReservationDTO reservation) {
        storage.put(reservation.getId(), reservation);
    }

    @Override
    public ReservationDTO find(String id) {
        return Optional.ofNullable(storage.get(id))
                .orElseThrow(() -> new IllegalStateException(format("No reservation with id {0} found", id)));
    }

    public void clear() {
        storage.clear();
    }

    public ReservationDTO only() {
        return Iterables.getOnlyElement(storage.values());
    }

}
