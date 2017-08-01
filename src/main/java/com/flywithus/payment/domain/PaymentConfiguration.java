package com.flywithus.payment.domain;

import com.flywithus.payment.port.outgoing.PaymentOperatorPort;
import com.flywithus.payment.port.outgoing.PaymentRepositoryPort;
import com.flywithus.payment.port.outgoing.ReservationRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentConfiguration {

    @Bean
    PaymentApplicationService paymentApplicationService(PaymentOperatorPort paymentOperatorPort, PaymentRepositoryPort paymentRepositoryPort, ReservationRepositoryPort reservationRepositoryPort) {
        DateTimeFactory dateTimeFactory = new DateTimeFactory();
        PaymentFactory paymentFactory = new PaymentFactory();
        ReservationFactory reservationFactory = new ReservationFactory();

        ReservationRepository reservationRepository = new ReservationRepository(reservationRepositoryPort, reservationFactory);
        PaymentRepository paymentRepository = new PaymentRepository(paymentRepositoryPort, paymentFactory, reservationRepository);

        PaymentOperator paymentOperator = new PaymentOperator(paymentOperatorPort);

        return new PaymentApplicationService(paymentOperator, paymentFactory, paymentRepository, reservationRepository, dateTimeFactory);
    }

}
