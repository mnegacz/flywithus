package com.flywithus.payment.port.incoming;

import com.flywithus.payment.command.ReceivePaymentCommand;

public interface ReceivePaymentPort {

    void receive(ReceivePaymentCommand command);

}
