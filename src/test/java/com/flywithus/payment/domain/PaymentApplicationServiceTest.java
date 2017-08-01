package com.flywithus.payment.domain;

import com.flywithus.payment.command.ReceivePaymentCommand;
import com.flywithus.payment.command.RegisterPaymentCommand;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PaymentApplicationServiceTest {

    private static final ReservationId RESERVATION_ID = ReservationId.of("reservation id");
    private static final Money AMOUNT = Money.of(BigDecimal.ONE);
    private static final PaymentId PAYMENT_ID = PaymentId.of("payment id");

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private PaymentOperator paymentOperator;

    @Mock
    private PaymentFactory paymentFactory;

    @Mock
    private Payment payment;

    @Mock
    private Reservation reservation;

    @Mock
    private DateTimeFactory dateTimeFactory;

    @Mock
    private DateTime now;

    @InjectMocks
    private PaymentApplicationService testee;

    @Test
    public void shouldRegisterPayment() {
        // given
        RegisterPaymentCommand command = new RegisterPaymentCommand(RESERVATION_ID.id());

        given(reservationRepository.find(RESERVATION_ID)).willReturn(reservation);
        given(reservation.amount()).willReturn(AMOUNT);
        given(paymentOperator.requestPayment(AMOUNT)).willReturn(PAYMENT_ID);
        given(paymentFactory.createPayment(PAYMENT_ID, reservation)).willReturn(payment);

        // when
        testee.register(command);

        // then
        verify(paymentOperator).requestPayment(AMOUNT);
        verify(paymentRepository).save(payment);
    }

    @Test
    public void shouldRegisterPaymentThrowIllegalArgumentExceptionWhenCommandIsNull() {
        // given
        RegisterPaymentCommand command = null;

        // when
        Throwable result = catchThrowable(() -> testee.register(command));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldReceivePayment() {
        // given
        ReceivePaymentCommand command = new ReceivePaymentCommand(PAYMENT_ID.id());

        given(paymentRepository.find(PAYMENT_ID)).willReturn(payment);
        given(payment.id()).willReturn(PAYMENT_ID);
        given(payment.reservation()).willReturn(reservation);
        given(reservation.id()).willReturn(RESERVATION_ID);

        // when
        testee.receive(command);

        // then
        verify(payment).receive();
        verify(paymentRepository).save(payment);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCommandIsNull() {
        // given
        ReceivePaymentCommand command = null;

        // when
        Throwable result = catchThrowable(() -> testee.receive(command));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldExpireNotReceivedPayments() {
        // given
        given(paymentRepository.findAllNotReceivedPayments()).willReturn(ImmutableList.of(payment));
        given(dateTimeFactory.now()).willReturn(now);
        given(payment.canBeExpired(now)).willReturn(true);
        given(payment.id()).willReturn(PAYMENT_ID);
        given(payment.reservation()).willReturn(reservation);
        given(reservation.id()).willReturn(RESERVATION_ID);

        // when
        testee.expireNotReceivedPayments();

        // then
        verify(paymentOperator).expirePayment(PAYMENT_ID);
        verify(payment).expire(now);
        verify(paymentRepository).save(payment);
    }

}