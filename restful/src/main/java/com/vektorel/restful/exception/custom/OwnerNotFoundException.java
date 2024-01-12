package com.vektorel.restful.exception.custom;

public class OwnerNotFoundException extends RuntimeException{

    public OwnerNotFoundException (String message){
        super(message);
    }
    public OwnerNotFoundException(){
        super();
    }
}
