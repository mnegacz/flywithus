package com.flywithus.user.domain;

import com.flywithus.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Builder
@Getter
class User {

  private UserId id;
  private Username username;
  private Password password;

  UserDto toDTO() {
    return UserDto.builder()
        .id(id.id())
        .username(username.value())
        .password(password.value())
        .build();
  }
}
