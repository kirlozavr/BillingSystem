package com.application.billingsystem.exceptions;

public class TariffNotFoundException extends RuntimeException{
    public TariffNotFoundException(String message) {
        super(message);
    }
}
