package com.flywithus.payment.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.flywithus.payment.port.outgoing.PaymentOperatorPort;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentOperatorTest {

  private static final Money AMOUNT = Money.of(BigDecimal.ONE);
  private static final PaymentId PAYMENT_ID = PaymentId.of("payment id");

  @Mock private PaymentOperatorPort paymentOperatorPort;

  @InjectMocks private PaymentOperator testee;

  @Test
  public void shouldRequestPayment() {
    // given
    given(paymentOperatorPort.requestPayment(AMOUNT.amount())).willReturn(PAYMENT_ID.id());

    // when
    PaymentId result = testee.requestPayment(AMOUNT);

    // then
    verify(paymentOperatorPort).requestPayment(AMOUNT.amount());

    assertThat(result).isEqualTo(PAYMENT_ID);
  }

  @Test
  public void shouldRequestPaymentThrowIllegalArgumentExceptionWhenAmountIsNull() {
    // given
    Money anotherAmount = null;

    // when
    Throwable result = catchThrowable(() -> testee.requestPayment(anotherAmount));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldExpirePayment() {
    // when
    testee.expirePayment(PAYMENT_ID);

    // then
    verify(paymentOperatorPort).expirePayment(PAYMENT_ID.id());
  }

  @Test
  public void shouldExpirePaymentThrowIllegalArgumentExceptionWhenIdIsNull() {
    // given
    PaymentId anotherId = null;

    // when
    Throwable result = catchThrowable(() -> testee.expirePayment(anotherId));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
