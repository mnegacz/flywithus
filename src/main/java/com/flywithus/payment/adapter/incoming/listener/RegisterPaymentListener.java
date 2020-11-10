package com.flywithus.payment.adapter.incoming.listener;

import com.flywithus.payment.command.RegisterPaymentCommand;
import com.flywithus.payment.port.incoming.RegisterPaymentPort;
import com.flywithus.reservation.event.ReservationMadeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class RegisterPaymentListener implements ApplicationListener<ReservationMadeEvent> {

  private final RegisterPaymentPort registerPaymentPort;

  public RegisterPaymentListener(RegisterPaymentPort registerPaymentPort) {
    this.registerPaymentPort = registerPaymentPort;
  }

  @Override
  public void onApplicationEvent(ReservationMadeEvent event) {
    registerPaymentPort.register(new RegisterPaymentCommand(event.getId()));
  }
}
