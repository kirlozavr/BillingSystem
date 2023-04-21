package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class PaymentCreateDto {
    private String numberPhone;
    private float money;
}
