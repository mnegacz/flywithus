package com.flywithus.reservation.domain;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class DiscountPolicyTest {

    private static final String USER_ID = "user id";
    private static final int FIVE = 5;

    private DiscountPolicy testee = new DiscountPolicy();

    @Test
    public void shouldReturnEmptyWhenUserIsUnregistered() {
        // given
        Client user = new UnregisteredUser();

        // when
        Optional<Discount> result = testee.findDiscount(user);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnDiscountOfFivePercentWhenUserIsRegistered() {
        // given
        Client user = new RegisteredUser(UserId.of(USER_ID));

        // when
        Optional<Discount> result = testee.findDiscount(user);

        // then
        assertThat(result).contains(Discount.of(Percent.of(FIVE)));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUserIsNull() {
        // given
        Client user = null;

        // when
        Throwable result = catchThrowable(() -> testee.findDiscount(user));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}