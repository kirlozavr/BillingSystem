package com.application.billingsystem.entity;

import lombok.Data;

@Data
public class CallDataRecordPlusEntity {
    private final String callType;
    private final String numberPhone;
    private final String startTime;
    private final String endTime;
    private final String tariffIndex;

    public CallDataRecordPlusEntity(
            String callType,
            String numberPhone,
            String startTime,
            String endTime,
            String tariffIndex
    ) {
        this.callType = callType;
        this.numberPhone = numberPhone;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tariffIndex = tariffIndex;
    }

    public CallDataRecordPlusEntity(CallDataRecordEntity callDataRecordEntity, String tariffIndex) {
        this.callType = callDataRecordEntity.getCallType();
        this.numberPhone = callDataRecordEntity.getNumberPhone();
        this.startTime = callDataRecordEntity.getStartTime();
        this.endTime = callDataRecordEntity.getEndTime();
        this.tariffIndex = tariffIndex;
    }
}
