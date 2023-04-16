package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class SubscriberDto {
    private final long id;
    private final String numberPhone;
    private final String tariffIndex;
    private final float balance;
}
