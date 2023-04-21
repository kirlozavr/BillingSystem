package com.application.billingsystem.dto;

import com.application.billingsystem.entity.PayloadEntity;
import lombok.Data;

import java.util.List;

@Data
public class SubscriberReportDto {

    private long id;
    private String numberPhone;
    private String tariffIndex;
    private final List<PayloadEntity> payloads;
    private float totalCost;
    private String monetaryUnit;

    public SubscriberReportDto(
            String numberPhone,
            String tariffIndex,
            List<PayloadEntity> payloads,
            float totalCost,
            String monetaryUnit
    ) {
        this.numberPhone = numberPhone;
        this.tariffIndex = tariffIndex;
        this.payloads = payloads;
        this.totalCost = totalCost;
        this.monetaryUnit = monetaryUnit;
    }

}
