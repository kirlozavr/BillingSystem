package com.application.billingsystem.exceptions;

public class PayNotFoundException extends RuntimeException{
    public PayNotFoundException(String message) {
        super(message);
    }
}
