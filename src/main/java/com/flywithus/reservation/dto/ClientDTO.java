package com.flywithus.reservation.dto;

import java.util.Optional;

public class ClientDTO {

  private String id;

  public ClientDTO() {}

  public ClientDTO(String id) {
    this.id = id;
  }

  public Optional<String> getId() {
    return Optional.ofNullable(id);
  }
}
