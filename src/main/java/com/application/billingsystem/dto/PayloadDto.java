package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class PayloadDto {

    private final long id;
    private final String callType;
    private final String startTime;
    private final String endTime;
    private final String duration;
    private final float cost;
}
