package com.flywithus.reservation.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static org.slf4j.LoggerFactory.getLogger;

import com.flywithus.reservation.command.CancelReservationCommand;
import com.flywithus.reservation.command.ChangeReservationCommand;
import com.flywithus.reservation.command.FindReservationCommand;
import com.flywithus.reservation.command.MakeReservationCommand;
import com.flywithus.reservation.dto.FindReservationDTO;
import com.flywithus.reservation.event.ReservationMadeEvent;
import com.flywithus.reservation.port.incoming.CancelReservationPort;
import com.flywithus.reservation.port.incoming.ChangeReservationPort;
import com.flywithus.reservation.port.incoming.FindReservationPort;
import com.flywithus.reservation.port.incoming.MakeReservationPort;
import com.flywithus.reservation.port.outgoing.EventPublisherPort;
import java.util.Optional;
import org.slf4j.Logger;

public class ReservationApplicationService
    implements MakeReservationPort,
        ChangeReservationPort,
        CancelReservationPort,
        FindReservationPort {

  private static final Logger LOG = getLogger(ReservationApplicationService.class);

  private final ClientRepository clientRepository;
  private final FlightRepository flightRepository;
  private final ReservationFactory reservationFactory;
  private final ReservationRepository reservationRepository;
  private final DateTimeFactory dateTimeFactory;
  private final EventPublisherPort eventPublisherPort;

  ReservationApplicationService(
      ClientRepository clientRepository,
      FlightRepository flightRepository,
      ReservationFactory reservationFactory,
      ReservationRepository reservationRepository,
      DateTimeFactory dateTimeFactory,
      EventPublisherPort eventPublisherPort) {
    this.clientRepository = clientRepository;
    this.flightRepository = flightRepository;
    this.reservationFactory = reservationFactory;
    this.reservationRepository = reservationRepository;
    this.dateTimeFactory = dateTimeFactory;
    this.eventPublisherPort = eventPublisherPort;
  }

  @Override
  public void make(MakeReservationCommand command) {
    assertNotNull(command, "command");

    Optional<UserId> userId = Optional.ofNullable(command.getUserId()).map(UserId::of);
    Client client = clientRepository.find(userId);
    Flight flight = flightRepository.find(FlightId.of(command.getFlightId()));
    NumberOfPeople numberOfPeople = NumberOfPeople.of(command.getNumberOfPeople());

    Reservation reservation = reservationFactory.createReservation(client, flight, numberOfPeople);

    reservationRepository.save(reservation);

    LOG.info("Reservation {} for flight {} has been made", reservation.id(), flight.id());

    eventPublisherPort.publishEvent(new ReservationMadeEvent(reservation, reservation.id().id()));
  }

  @Override
  public void change(ChangeReservationCommand command) {
    assertNotNull(command, "command");

    Reservation reservation =
        reservationRepository.find(ReservationId.of(command.getReservationId()));
    Flight flight = flightRepository.find(FlightId.of(command.getFlightId()));
    NumberOfPeople numberOfPeople = NumberOfPeople.of(command.getNumberOfPeople());
    DateTime now = dateTimeFactory.now();

    reservation.change(flight, numberOfPeople, now);

    reservationRepository.save(reservation);

    LOG.info("Reservation {} for flight {} has been changed", reservation.id(), flight.id());
  }

  @Override
  public void cancel(CancelReservationCommand command) {
    assertNotNull(command, "command");

    Reservation reservation =
        reservationRepository.find(ReservationId.of(command.getReservationId()));
    DateTime now = dateTimeFactory.now();

    reservation.cancel(now);

    reservationRepository.save(reservation);

    LOG.info(
        "Reservation {} for flight {} has been cancelled",
        reservation.id(),
        reservation.flight().id());
  }

  @Override
  public FindReservationDTO find(FindReservationCommand command) {
    assertNotNull(command, "command");

    return reservationRepository.find(ReservationId.of(command.getId())).toFindReservationDTO();
  }
}
