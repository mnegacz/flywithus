package com.flywithus.payment.domain;

import com.flywithus.payment.port.outgoing.PaymentOperatorPort;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class PaymentOperator {

    private final PaymentOperatorPort paymentOperatorPort;

    PaymentOperator(PaymentOperatorPort paymentOperatorPort) {
        this.paymentOperatorPort = paymentOperatorPort;
    }

    PaymentId requestPayment(Money amount) {
        assertNotNull(amount, "amount");

        return PaymentId.of(paymentOperatorPort.requestPayment(amount.amount()));
    }

    void expirePayment(PaymentId id) {
        assertNotNull(id, "id");

        paymentOperatorPort.expirePayment(id.id());
    }

}
