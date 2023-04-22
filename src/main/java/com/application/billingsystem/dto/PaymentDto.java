package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private final long id;
    private final String numberPhone;
    private final long money;

    public PaymentDto(long id, String numberPhone, long money) {
        this.id = id;
        this.numberPhone = numberPhone;
        this.money = money;
    }
}
