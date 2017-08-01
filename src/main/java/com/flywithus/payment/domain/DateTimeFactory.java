package com.flywithus.payment.domain;

import java.time.LocalDateTime;

class DateTimeFactory {

    DateTime now() {
        return DateTime.of(LocalDateTime.now());
    }

}
