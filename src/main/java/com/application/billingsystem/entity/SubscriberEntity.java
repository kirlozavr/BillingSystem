package com.application.billingsystem.entity;

import lombok.Data;

@Data
public class SubscriberEntity {

    private final long id;
    private final String numberPhone;
    private final String tariffId;
    private final float balance;
}
