package com.example.E_Commerce_API.exceptions;

public class ItemNotFound extends RuntimeException{
    public ItemNotFound(String message){
        super(message);
    }
}
