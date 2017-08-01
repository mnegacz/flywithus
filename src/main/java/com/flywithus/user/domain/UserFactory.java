package com.flywithus.user.domain;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class UserFactory {

    User createUser(Username username, Password password) {
        assertNotNull(username, "username");
        assertNotNull(password, "password");

        return new User(UserId.generate(), username, password);
    }

}
