package com.flywithus.payment.port.outgoing;

import com.flywithus.payment.dto.PaymentDTO;
import java.util.List;

public interface PaymentRepositoryPort {

  void save(PaymentDTO payment);

  PaymentDTO find(String id);

  List<PaymentDTO> findAllExpirablePayments();
}
