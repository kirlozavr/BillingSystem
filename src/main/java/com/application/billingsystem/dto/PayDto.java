package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class PayDto {
    private final long id;
    private final String numberPhone;
    private final float money;

    public PayDto(long id, String numberPhone, float money) {
        this.id = id;
        this.numberPhone = numberPhone;
        this.money = money;
    }
}