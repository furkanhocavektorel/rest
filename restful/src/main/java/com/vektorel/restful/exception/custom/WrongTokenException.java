package com.vektorel.restful.exception.custom;

public class WrongTokenException extends RuntimeException{

    public WrongTokenException() {
    }

    public WrongTokenException(String message) {
        super(message);
    }
}
