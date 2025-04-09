package com.unicaes.poo.infra.exceptions;

public class ConsumableException extends RuntimeException {
    public ConsumableException(String message) {
        super(message);
    }

    public ConsumableException(String message, Throwable cause) {
        super(message, cause);
    }
}