package com.flywithus.reservation.port.outgoing;

public interface EventPublisherPort {

    void publishEvent(Object event);

}
