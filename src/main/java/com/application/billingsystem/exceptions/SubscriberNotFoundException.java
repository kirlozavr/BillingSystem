package com.application.billingsystem.exceptions;

public class SubscriberNotFoundException extends RuntimeException{
    public SubscriberNotFoundException(String message) {
        super(message);
    }
}
