package com.flywithus.reservation.adapter.outgoing;

import com.flywithus.reservation.port.outgoing.EventPublisherPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class EventPublisherAdapter implements EventPublisherPort {

  private final ApplicationEventPublisher applicationEventPublisher;

  public EventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void publishEvent(Object event) {
    applicationEventPublisher.publishEvent(event);
  }
}
