package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

import com.flywithus.user.command.RegisterUserCommand;
import com.flywithus.user.port.incoming.RegisterUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@RequiredArgsConstructor
@Slf4j
public class UserApplicationService implements RegisterUserPort {

  private final UserFactory userFactory;
  private final UserRepository userRepository;

  @Override
  public void register(RegisterUserCommand command) {
    assertNotNull(command, "command");

    val username = Username.of(command.getUsername());
    val password = Password.of(command.getPassword());
    val user = userFactory.createUser(username, password);
    userRepository.save(user);
    log.info("User {} has been created.", user.id());
  }
}
