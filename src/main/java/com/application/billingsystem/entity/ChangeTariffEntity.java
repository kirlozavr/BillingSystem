package com.application.billingsystem.entity;

import lombok.Data;

@Data
public class ChangeTariffEntity {

    private final long id;
    private final String numberPhone;
    private final String tariffId;
}
