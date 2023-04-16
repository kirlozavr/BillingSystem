package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class PayloadCreateDto {

    private String callType;
    private String startTime;
    private String endTime;
    private String duration;
    private float cost;
}
