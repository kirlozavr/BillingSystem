package com.application.billingsystem.exceptions;

public class SubscriberExistsException extends RuntimeException{
    public SubscriberExistsException(String message) {
        super(message);
    }
}
