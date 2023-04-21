package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.SubscriberReportEntity;

public class SubscriberReportMapper implements Mapper<SubscriberReportEntity, SubscriberReportDto> {

    @Override
    public SubscriberReportDto getEntityToDto(SubscriberReportEntity entity) {
        return new SubscriberReportDto(
                entity.getNumberPhone(),
                entity.getTariffIndex(),
                entity.getPayloads(),
                entity.getTotalCost(),
                entity.getMonetaryUnit()
        );
    }

    @Override
    public SubscriberReportEntity getDtoToEntity(SubscriberReportDto dto) {
        return new SubscriberReportEntity(
                dto.getNumberPhone(),
                dto.getTariffIndex(),
                dto.getPayloads(),
                dto.getTotalCost(),
                dto.getMonetaryUnit()
        );
    }
}
