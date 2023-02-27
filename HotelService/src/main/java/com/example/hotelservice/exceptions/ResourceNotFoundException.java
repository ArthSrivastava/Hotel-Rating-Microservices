package com.example.hotelservice.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("No hotel found with given id!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
