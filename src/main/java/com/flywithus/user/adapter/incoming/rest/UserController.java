package com.flywithus.user.adapter.incoming.rest;

import com.flywithus.user.command.RegisterUserCommand;
import com.flywithus.user.port.incoming.RegisterUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UserController {

  private final RegisterUserPort registerUserPort;

  @PostMapping("/users")
  void register(@RequestBody RegisterUserCommand command) {
    registerUserPort.register(command);
  }
}
