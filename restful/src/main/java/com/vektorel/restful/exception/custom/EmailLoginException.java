package com.vektorel.restful.exception.custom;

public class EmailLoginException extends RuntimeException{

    public EmailLoginException(String message){
        super(message);
    }
}
