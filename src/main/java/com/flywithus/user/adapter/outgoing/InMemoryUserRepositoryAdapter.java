package com.flywithus.user.adapter.outgoing;

import static java.text.MessageFormat.format;

import com.flywithus.user.dto.UserDTO;
import com.flywithus.user.port.outgoing.UserRepositoryPort;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepositoryAdapter implements UserRepositoryPort {

  private ConcurrentHashMap<String, UserDTO> store = new ConcurrentHashMap<>();

  @Override
  public void save(UserDTO user) {
    store.put(user.getId(), user);
  }

  public UserDTO findByUsername(String name) {
    return store.values().stream()
        .filter(dto -> name.equals(dto.getUsername()))
        .findFirst()
        .orElseThrow(() -> new IllegalStateException(format("no user with name {0} found", name)));
  }

  public void clear() {
    store.clear();
  }
}
