package com.flywithus.payment.domain;

import com.flywithus.payment.command.ReceivePaymentCommand;
import com.flywithus.payment.command.RegisterPaymentCommand;
import com.flywithus.payment.port.incoming.ExpireNotReceivedPaymentsPort;
import com.flywithus.payment.port.incoming.ReceivePaymentPort;
import com.flywithus.payment.port.incoming.RegisterPaymentPort;
import org.slf4j.Logger;

import java.util.List;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

public class PaymentApplicationService implements RegisterPaymentPort, ReceivePaymentPort, ExpireNotReceivedPaymentsPort {

    private static final Logger LOG = getLogger(PaymentApplicationService.class);

    private final PaymentOperator paymentOperator;
    private final PaymentFactory paymentFactory;
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final DateTimeFactory dateTimeFactory;

    PaymentApplicationService(PaymentOperator paymentOperator, PaymentFactory paymentFactory, PaymentRepository paymentRepository, ReservationRepository reservationRepository, DateTimeFactory dateTimeFactory) {
        this.paymentOperator = paymentOperator;
        this.paymentFactory = paymentFactory;
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.dateTimeFactory = dateTimeFactory;
    }

    @Override
    public void register(RegisterPaymentCommand command) {
        assertNotNull(command, "command");

        ReservationId reservationId = ReservationId.of(command.getReservationId());
        Reservation reservation = reservationRepository.find(reservationId);
        PaymentId paymentId = paymentOperator.requestPayment(reservation.amount());
        Payment payment = paymentFactory.createPayment(paymentId, reservation);

        paymentRepository.save(payment);

        LOG.info("Payment {} for reservation {} has been registered.", paymentId, reservationId);
    }

    @Override
    public void receive(ReceivePaymentCommand command) {
        assertNotNull(command, "command");

        Payment payment = paymentRepository.find(PaymentId.of(command.getPaymentId()));
        payment.receive();
        paymentRepository.save(payment);

        LOG.info("Payment {} for reservation {} has been received.", payment.id(), payment.reservation().id());
    }

    @Override
    public void expireNotReceivedPayments() {
        DateTime now = dateTimeFactory.now();

        List<Payment> payments = paymentRepository.findAllNotReceivedPayments()
                .stream()
                .filter(payment -> payment.canBeExpired(now))
                .collect(toList());

        payments.forEach(payment -> expirePayment(payment, now));
    }

    private void expirePayment(Payment payment, DateTime now) {
        paymentOperator.expirePayment(payment.id());
        payment.expire(now);
        paymentRepository.save(payment);

        LOG.info("Payment {} for reservation {} has been expired.", payment.id(), payment.reservation().id());
    }

}
