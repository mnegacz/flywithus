package com.flywithus.payment.adapter.incoming.rest;

import com.flywithus.payment.command.ReceivePaymentCommand;
import com.flywithus.payment.port.incoming.ReceivePaymentPort;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
class PaymentController {

    private final ReceivePaymentPort receivePaymentPort;

    PaymentController(ReceivePaymentPort receivePaymentPort) {
        this.receivePaymentPort = receivePaymentPort;
    }

    @PutMapping
    void receive(@RequestBody ReceivePaymentCommand command) {
        receivePaymentPort.receive(command);
    }

}
