package com.unicaes.poo.infra.exceptions;

public class QueryException extends RuntimeException {
    public QueryException(String message) {
        super(message);
    }
}
