package com.flywithus.reservation.domain;

import java.time.LocalDateTime;

class DateTimeFactory {

  DateTime now() {
    return DateTime.of(LocalDateTime.now());
  }
}
