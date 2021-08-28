package com.manish.exceptions;

import java.util.function.Supplier;

public class NonDeterministicStateException extends RuntimeException {

    private static final Supplier<String> DEFAULT_MESSAGE_SUPPLIER = () -> "We are not reach to any conclusion";

    public NonDeterministicStateException() {
        this(DEFAULT_MESSAGE_SUPPLIER.get());
    }

    public NonDeterministicStateException(final String message) {
        super(message);
    }

    public NonDeterministicStateException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
