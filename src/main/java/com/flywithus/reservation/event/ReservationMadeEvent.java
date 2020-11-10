package com.flywithus.reservation.event;

import org.springframework.context.ApplicationEvent;

public class ReservationMadeEvent extends ApplicationEvent {

  private final String id;

  public ReservationMadeEvent(Object source, String id) {
    super(source);
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
