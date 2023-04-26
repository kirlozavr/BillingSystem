package com.application.billingsystem.billing.tariffs;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;
import com.application.billingsystem.entity.PayloadEntity;
import com.application.billingsystem.entity.SubscriberReportEntity;
import com.application.billingsystem.utils.DateMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PerMinuteTariff implements Tariff{

    private static final String TARIFF_INDEX = "03";
    private static final String MONETARY_UNIT = "rubles";
    private static final float COST_PER_MINUTE = 1.5f;

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
        final float cost = minute * COST_PER_MINUTE;

        return new PayloadEntity(
                entity.getCallType(),
                DateMapper.getStringToDateTimeString(entity.getStartTime()),
                DateMapper.getStringToDateTimeString(entity.getEndTime()),
                DateMapper.getDurationToString(entity.getStartTime(), entity.getEndTime()),
                cost
        );
    }
}
