package com.flywithus.infrastructure.assertions;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.text.MessageFormat.format;

public class ArgumentAssertions {

    public static void assertNotNull(Object argument, String argumentName) {
        if (argument == null) {
            throw new IllegalArgumentException(format("argument {0} cannot be null", argumentName));
        }
    }

    public static void assertNotNegative(BigDecimal argument, String argumentName) {
        if (ZERO.compareTo(argument) > 0) {
            throw new IllegalArgumentException(format("argument {0} cannot be negative", argumentName));
        }
    }

}
