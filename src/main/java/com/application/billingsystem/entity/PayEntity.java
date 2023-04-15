package com.application.billingsystem.entity;

import lombok.Data;

@Data
public class PayEntity {

    private final long id;
    private final String numberPhone;
    private final float money;
}
