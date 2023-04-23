package com.application.billingsystem.exceptions;

public class TariffExistsException extends RuntimeException{
    public TariffExistsException(String message) {
        super(message);
    }
}
