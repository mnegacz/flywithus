package com.flywithus.payment.domain;

import com.flywithus.payment.dto.PaymentDTO;
import com.flywithus.payment.port.outgoing.PaymentRepositoryPort;

import java.util.List;

import static java.util.stream.Collectors.toList;

class PaymentRepository {

    private final PaymentRepositoryPort paymentRepositoryPort;
    private final PaymentFactory paymentFactory;
    private final ReservationRepository reservationRepository;

    PaymentRepository(PaymentRepositoryPort paymentRepositoryPort, PaymentFactory paymentFactory, ReservationRepository reservationRepository) {
        this.paymentRepositoryPort = paymentRepositoryPort;
        this.paymentFactory = paymentFactory;
        this.reservationRepository = reservationRepository;
    }

    void save(Payment payment) {
        paymentRepositoryPort.save(payment.toDTO());
    }

    Payment find(PaymentId id) {
        return convertPaymentDTOToPayment(paymentRepositoryPort.find(id.id()));
    }

    private Payment convertPaymentDTOToPayment(PaymentDTO paymentDTO) {
        PaymentId id = PaymentId.of(paymentDTO.getId());
        ReservationId reservationId = ReservationId.of(paymentDTO.getReservationDTO().getId());
        Reservation reservation = reservationRepository.find(reservationId);
        PaymentStatus status = PaymentStatus.valueOf(paymentDTO.getStatus().name());

        return paymentFactory.createPayment(id, reservation, status);
    }

    List<Payment> findAllNotReceivedPayments() {
        return paymentRepositoryPort.findAllExpirablePayments()
                .stream()
                .map(this::convertPaymentDTOToPayment)
                .collect(toList());
    }

}
