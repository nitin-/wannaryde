package com.example.service.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
    	super(message);
    }

}
