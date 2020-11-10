package com.flywithus.user.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

  String id;
  String username;
  char[] password;
}
