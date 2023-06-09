package com.application.billingsystem.billing.tariffs;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;
import com.application.billingsystem.entity.PayloadEntity;
import com.application.billingsystem.entity.SubscriberReportEntity;
import com.application.billingsystem.utils.DateMapper;

import java.util.List;

public class OrdinaryTariff implements Tariff{

    private static final String TARIFF_INDEX = "11";
    private static final String MONETARY_UNIT = "rubles";
    private static final int MINUTE_LIMIT = 100;
    private static final float COST_BEFORE_LIMIT = 0.5f;
    private static final float COST_AFTER_LIMIT = 1.5f;

    private int totalMinute = 0;

    @Override
    public SubscriberReportDto calculate(List<CallDataRecordPlusEntity> entityList) {
        final String numberPhone = entityList.get(0).getNumberPhone();

        final SubscriberReportEntity subscriberReport =
                new SubscriberReportEntity(
                        numberPhone,
                        TARIFF_INDEX,
                        0,
                        MONETARY_UNIT
                );

        entityList
                .stream()
                .forEach(entity -> subscriberReport.addPayload(calculatePayload(entity)));

        subscriberReport
                .getPayloads()
                .stream()
                .forEach(payload -> subscriberReport
                        .setTotalCost(subscriberReport.getTotalCost() + payload.getCost())
                );

        return MAPPER.getEntityToDto(subscriberReport);
    }

    private PayloadEntity calculatePayload(CallDataRecordPlusEntity entity){
        final int minute = DateMapper.getDurationToMinute(
                entity.getStartTime(),
                entity.getEndTime()
        );
        float cost = 0;
        int minutesBefore = 0; // Продолжительность минут до превышения лимита
        int minutesAfter = 0; // Продолжительность минут после превышения лимита

        if(entity.getCallType().equals("01")){

            if(totalMinute >= MINUTE_LIMIT){
                minutesAfter = minute;
                minutesBefore = 0;
            } else if ((totalMinute + minute) >= MINUTE_LIMIT){
                minutesAfter = (totalMinute + minute) - MINUTE_LIMIT;
                minutesBefore = minute - minutesAfter;
            } else {
                minutesBefore = minute;
                minutesAfter = 0;
            }

            cost = minutesBefore * COST_BEFORE_LIMIT + minutesAfter * COST_AFTER_LIMIT;
            totalMinute += minute;
        }

        return new PayloadEntity(
                entity.getCallType(),
                DateMapper.getStringToDateTimeString(entity.getStartTime()),
                DateMapper.getStringToDateTimeString(entity.getEndTime()),
                DateMapper.getDurationToString(entity.getStartTime(), entity.getEndTime()),
                cost
        );
    }
}
