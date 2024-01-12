package com.vektorel.restful.exception.custom;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String message){
        super(message);
    }

    public EmailAlreadyExistsException(){super();}
}
