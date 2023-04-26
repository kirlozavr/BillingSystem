package com.application.billingsystem.billing.tariffs;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;
import com.application.billingsystem.mapping.SubscriberReportMapper;

import java.util.List;

public interface Tariff {

    SubscriberReportMapper MAPPER = new SubscriberReportMapper();
    SubscriberReportDto calculate(List<CallDataRecordPlusEntity> entityList);
}
