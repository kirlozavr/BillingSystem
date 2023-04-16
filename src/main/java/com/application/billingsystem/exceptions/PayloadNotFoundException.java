package com.application.billingsystem.exceptions;

public class PayloadNotFoundException extends RuntimeException{
    public PayloadNotFoundException(String message) {
        super(message);
    }
}
