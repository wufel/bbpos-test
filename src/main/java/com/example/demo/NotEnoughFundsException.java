package com.example.demo;

public class NotEnoughFundsException extends RuntimeException{
    public NotEnoughFundsException(String message) {
        super(message);
    }
}
