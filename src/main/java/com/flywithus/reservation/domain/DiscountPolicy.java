package com.flywithus.reservation.domain;

import java.util.Optional;

import static com.flywithus.infrastructure.assertions.ArgumentAssertions.assertNotNull;

class DiscountPolicy {

    private static final int REGISTERED_USER_DISCOUNT_PERCENT = 5;

    Optional<Discount> findDiscount(Client client) {
        assertNotNull(client, "client");

        if (client instanceof RegisteredUser) {
            return Optional.of(Discount.of(Percent.of(REGISTERED_USER_DISCOUNT_PERCENT)));
        }
        return Optional.empty();
    }

}
