package com.flywithus.user.port.outgoing;

import com.flywithus.user.dto.UserDTO;

public interface UserRepositoryPort {

    void save(UserDTO user);

}
