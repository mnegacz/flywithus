package com.flywithus.flight.domain;

import com.flywithus.flight.port.outgoing.FlightRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FlightConfiguration {

  @Bean
  FlightApplicationService flightApplicationService(FlightRepositoryPort flightRepositoryPort) {
    FlightFactory flightFactory = new FlightFactory();
    FlightRepository flightRepository = new FlightRepository(flightRepositoryPort, flightFactory);

    return new FlightApplicationService(flightRepository);
  }
}
