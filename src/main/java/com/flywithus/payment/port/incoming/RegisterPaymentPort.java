package com.flywithus.payment.port.incoming;

import com.flywithus.payment.command.RegisterPaymentCommand;

public interface RegisterPaymentPort {

  void register(RegisterPaymentCommand command);
}
