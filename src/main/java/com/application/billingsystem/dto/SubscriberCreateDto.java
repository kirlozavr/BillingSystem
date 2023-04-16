package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class SubscriberCreateDto {

    private String numberPhone;
    private String tariffIndex;
    private float balance;
}
