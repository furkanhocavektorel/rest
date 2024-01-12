package com.vektorel.restful.exception.custom;

public class TokenNotEmptyException extends RuntimeException {

    public TokenNotEmptyException() {
    }

    public TokenNotEmptyException(String message) {
        super(message);
    }
}
