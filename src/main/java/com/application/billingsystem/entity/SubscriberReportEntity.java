package com.application.billingsystem.entity;

import lombok.Data;

import java.util.List;

@Data
public class SubscriberReportEntity {

    private final long id;
    private final String numberPhone;
    private final String tariffId;
    private final List<PayloadEntity> payloads;
    private final float totalCost;
    private final String monetaryUnit;
}
