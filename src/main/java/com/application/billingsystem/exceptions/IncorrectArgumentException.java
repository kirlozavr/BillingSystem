package com.application.billingsystem.exceptions;

public class IncorrectArgumentException extends RuntimeException{
    public IncorrectArgumentException(String message) {
        super(message);
    }
}
