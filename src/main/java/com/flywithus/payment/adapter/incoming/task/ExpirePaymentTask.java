package com.flywithus.payment.adapter.incoming.task;

import com.flywithus.payment.port.incoming.ExpireNotReceivedPaymentsPort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpirePaymentTask {

  private final ExpireNotReceivedPaymentsPort expireNotReceivedPaymentsPort;

  public ExpirePaymentTask(ExpireNotReceivedPaymentsPort expireNotReceivedPaymentsPort) {
    this.expireNotReceivedPaymentsPort = expireNotReceivedPaymentsPort;
  }

  @Scheduled(cron = "0 0 * * * *")
  public void expire() {
    expireNotReceivedPaymentsPort.expireNotReceivedPayments();
  }
}
