package com.vektorel.restful.exception.custom;

public class PasswordLoginException extends RuntimeException {
    public PasswordLoginException(String message){
        super(message);
    }
}
