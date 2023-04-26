package com.application.billingsystem.billing.tariffs;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;

import java.util.List;

public interface Tariff {

    SubscriberReportDto calculate(List<CallDataRecordPlusEntity> entityList);
}
