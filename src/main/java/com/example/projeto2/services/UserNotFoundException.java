package com.example.projeto2.services;

public class UserNotFoundException extends Throwable {

    public UserNotFoundException(String message) {
        super(message);
    }
}
