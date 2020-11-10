package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;
import static org.slf4j.LoggerFactory.getLogger;

import com.flywithus.user.command.RegisterUserCommand;
import com.flywithus.user.port.incoming.RegisterUserPort;
import org.slf4j.Logger;

public class UserApplicationService implements RegisterUserPort {

  private static final Logger LOG = getLogger(UserApplicationService.class);

  private final UserFactory userFactory;
  private final UserRepository userRepository;

  UserApplicationService(UserFactory userFactory, UserRepository userRepository) {
    this.userFactory = userFactory;
    this.userRepository = userRepository;
  }

  @Override
  public void register(RegisterUserCommand command) {
    assertNotNull(command, "command");

    Username username = Username.of(command.getUsername());
    Password password = Password.of(command.getPassword());

    User user = userFactory.createUser(username, password);
    userRepository.save(user);

    LOG.info("User {} has been created.", user.id());
  }
}
