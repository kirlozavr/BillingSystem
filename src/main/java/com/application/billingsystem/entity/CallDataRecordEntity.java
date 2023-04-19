package com.application.billingsystem.entity;

import lombok.Data;

@Data
public class CallDataRecordEntity {

    private final String callType;
    private final String numberPhone;
    private final String startTime;
    private final String endTime;
}
