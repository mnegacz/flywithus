package com.flywithus.reservation.domain;

import com.flywithus.reservation.port.outgoing.EventPublisherPort;
import com.flywithus.reservation.port.outgoing.FlightRepositoryPort;
import com.flywithus.reservation.port.outgoing.ReservationRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReservationConfiguration {

  @Bean
  ReservationApplicationService reservationApplicationService(
      FlightRepositoryPort flightRepositoryPort,
      ReservationRepositoryPort reservationRepositoryPort,
      EventPublisherPort eventPublisherPort) {
    ClientFactory clientFactory = new ClientFactory();
    DiscountPolicy discountPolicy = new DiscountPolicy();
    DateTimeFactory dateTimeFactory = new DateTimeFactory();
    ReservationFactory reservationFactory = new ReservationFactory(dateTimeFactory, discountPolicy);

    ClientRepository clientRepository = new ClientRepository(clientFactory);
    FlightRepository flightRepository = new FlightRepository(flightRepositoryPort);
    ReservationRepository reservationRepository =
        new ReservationRepository(
            reservationRepositoryPort, clientRepository, flightRepository, reservationFactory);

    return new ReservationApplicationService(
        clientRepository,
        flightRepository,
        reservationFactory,
        reservationRepository,
        dateTimeFactory,
        eventPublisherPort);
  }
}
