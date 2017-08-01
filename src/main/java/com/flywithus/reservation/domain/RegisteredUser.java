package com.flywithus.reservation.domain;

import com.flywithus.reservation.dto.ClientDTO;

class RegisteredUser implements Client {

    private final UserId id;

    RegisteredUser(UserId id) {
        this.id = id;
    }

    @Override
    public ClientDTO toDTO() {
        return new ClientDTO(id.id());
    }

}
