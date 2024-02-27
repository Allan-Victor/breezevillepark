package com.breezevillepark.Kitchen.and.Bar.System.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
