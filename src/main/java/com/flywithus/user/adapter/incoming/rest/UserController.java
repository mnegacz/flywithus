package com.flywithus.user.adapter.incoming.rest;

import com.flywithus.user.command.RegisterUserCommand;
import com.flywithus.user.port.incoming.RegisterUserPort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
class UserController {

    private final RegisterUserPort registerUserPort;

    UserController(RegisterUserPort registerUserPort) {
        this.registerUserPort = registerUserPort;
    }

    @PostMapping
    void register(@RequestBody RegisterUserCommand command) {
        registerUserPort.register(command);
    }

}
