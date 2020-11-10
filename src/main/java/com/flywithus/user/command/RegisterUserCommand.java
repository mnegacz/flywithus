package com.flywithus.user.command;

import lombok.Value;

@Value
public class RegisterUserCommand {

  String username;
  char[] password;
}
