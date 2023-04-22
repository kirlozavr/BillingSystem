package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class SubscriberCreateDto {

    private String numberPhone;
    private String tariffIndex;
    private long balance;

    public SubscriberCreateDto(String numberPhone, String tariffIndex, long balance) {
        this.numberPhone = numberPhone;
        this.tariffIndex = tariffIndex;
        this.balance = balance;
    }
}
