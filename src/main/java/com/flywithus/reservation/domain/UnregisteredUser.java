package com.flywithus.reservation.domain;

import com.flywithus.reservation.dto.ClientDTO;

class UnregisteredUser implements Client {

    @Override
    public ClientDTO toDTO() {
        return new ClientDTO();
    }

}
