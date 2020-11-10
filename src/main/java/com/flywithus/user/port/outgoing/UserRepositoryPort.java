package com.flywithus.user.port.outgoing;

import com.flywithus.user.dto.UserDto;

public interface UserRepositoryPort {

  void save(UserDto user);
}
