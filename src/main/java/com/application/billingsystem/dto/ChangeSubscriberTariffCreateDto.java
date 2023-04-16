package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class ChangeSubscriberTariffCreateDto {
    private String numberPhone;
    private String tariffIndex;
}
