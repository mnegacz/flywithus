package com.flywithus.reservation.domain;

import com.flywithus.reservation.dto.ReservationDTO;
import com.flywithus.reservation.port.outgoing.ReservationRepositoryPort;
import java.util.Optional;

class ReservationRepository {

  private final ReservationRepositoryPort reservationRepositoryPort;
  private final ClientRepository clientRepository;
  private final FlightRepository flightRepository;
  private final ReservationFactory reservationFactory;

  ReservationRepository(
      ReservationRepositoryPort reservationRepositoryPort,
      ClientRepository clientRepository,
      FlightRepository flightRepository,
      ReservationFactory reservationFactory) {
    this.reservationRepositoryPort = reservationRepositoryPort;
    this.clientRepository = clientRepository;
    this.flightRepository = flightRepository;
    this.reservationFactory = reservationFactory;
  }

  void save(Reservation reservation) {
    reservationRepositoryPort.save(reservation.toDTO());
  }

  Reservation find(ReservationId id) {
    ReservationDTO dto = reservationRepositoryPort.find(id.id());

    Optional<UserId> clientId = dto.getUser().getId().map(UserId::of);
    FlightId flightId = FlightId.of(dto.getFlight().getId());

    Client client = clientRepository.find(clientId);
    Flight flight = flightRepository.find(flightId);
    NumberOfPeople numberOfPeople = NumberOfPeople.of(dto.getNumberOfPeople());
    DateTime dateTime = DateTime.of(dto.getDateTime());
    ReservationStatus status = ReservationStatus.valueOf(dto.getStatus().name());
    Optional<Discount> discount =
        Optional.ofNullable(dto.getDiscount())
            .map(discountDTO -> Discount.of(Percent.of(discountDTO.getPercent())));

    return reservationFactory.createReservation(
        id, client, flight, dateTime, numberOfPeople, discount, status);
  }
}
