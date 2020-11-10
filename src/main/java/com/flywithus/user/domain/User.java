package com.flywithus.user.domain;

import com.flywithus.user.dto.UserDTO;

class User {

  private UserId id;
  private Username username;
  private Password password;

  User(UserId id, Username username, Password password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  UserId id() {
    return id;
  }

  Username username() {
    return username;
  }

  Password password() {
    return password;
  }

  UserDTO toDTO() {
    return new UserDTO(id.id(), username.username(), password.password());
  }
}
