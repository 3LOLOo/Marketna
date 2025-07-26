package com.example.E_Commerce_API.exceptions;

public class InsufficientBalance extends RuntimeException{
    public InsufficientBalance(String message) {
        super(message);
    }
}
