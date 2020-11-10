package com.flywithus.user.port.incoming;

import com.flywithus.user.command.RegisterUserCommand;

public interface RegisterUserPort {

  void register(RegisterUserCommand command);
}
