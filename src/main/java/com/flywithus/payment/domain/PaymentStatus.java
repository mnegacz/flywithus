package com.flywithus.payment.domain;

import com.flywithus.payment.dto.PaymentStatusDTO;

enum PaymentStatus {

    CREATED, RECEIVED, EXPIRED;

    PaymentStatusDTO toDTO() {
        return PaymentStatusDTO.valueOf(this.name());
    }

}
