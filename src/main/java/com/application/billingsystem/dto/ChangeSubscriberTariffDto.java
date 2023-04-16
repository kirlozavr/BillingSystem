package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class ChangeSubscriberTariffDto {

    private final long id;
    private final String numberPhone;
    private final String tariffIndex;
}
