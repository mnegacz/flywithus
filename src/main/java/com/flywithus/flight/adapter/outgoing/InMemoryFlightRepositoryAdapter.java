package com.flywithus.flight.adapter.outgoing;

import com.flywithus.flight.dto.FlightDTO;
import com.flywithus.flight.dto.SearchFlightCriteria;
import com.flywithus.flight.port.outgoing.FlightRepositoryPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.toList;

@Repository
public class InMemoryFlightRepositoryAdapter implements FlightRepositoryPort {

    private ConcurrentHashMap<String, FlightDTO> storage = new ConcurrentHashMap<>();

    @Override
    public List<FlightDTO> findAll(SearchFlightCriteria criteria) {
        return storage.values()
                .stream()
                .filter(dto -> match(dto, criteria))
                .collect(toList());
    }

    @Override
    public FlightDTO find(String id) {
        return Optional.ofNullable(storage.get(id))
                .orElseThrow(() -> new IllegalStateException(format("No flight with id {0} found", id)));
    }

    private boolean match(FlightDTO dto, SearchFlightCriteria criteria) {
        return matchPlace(criteria.getArrivalPlace(), dto.getArrivalPlace())
                && matchPlace(criteria.getDeparturePlace(), dto.getDeparturePlace())
                && matchDateTime(criteria.getArrivalDate(), dto.getArrivalDateTime().toLocalDate())
                && matchDateTime(criteria.getDepartureDate(), dto.getDepartureDateTime().toLocalDate())
                && matchNumberOfPeople(criteria.getNumberOfPeople(), dto.getAvailableCapacity());

    }

    private boolean matchNumberOfPeople(Optional<Integer> numberOfPeople, Integer availableCapacity) {
        return !numberOfPeople.isPresent() || numberOfPeople.filter(number -> availableCapacity >= number).isPresent();
    }

    private boolean matchDateTime(Optional<LocalDate> criteriaDateTime, LocalDate dateTime) {
        return !criteriaDateTime.isPresent() || criteriaDateTime.filter(dateTime::equals).isPresent();
    }

    private boolean matchPlace(Optional<String> criteriaPlace, String place) {
        return !criteriaPlace.isPresent() || criteriaPlace.filter(place::contains).isPresent();
    }

    public void save(FlightDTO dto) {
        storage.put(dto.getId(), dto);
    }

    public void clear() {
        storage.clear();
    }

}
