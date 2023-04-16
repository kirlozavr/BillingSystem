package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.ChangeSubscriberTariffCreateDto;
import com.application.billingsystem.dto.ChangeSubscriberTariffDto;
import com.application.billingsystem.entity.ChangeSubscriberTariffEntity;

public class ChangeSubscriberTariffMapper implements Mapper<ChangeSubscriberTariffEntity, ChangeSubscriberTariffDto>{
    @Override
    public ChangeSubscriberTariffDto getEntityToDto(ChangeSubscriberTariffEntity entity) {
        return new ChangeSubscriberTariffDto(
                entity.getId(),
                entity.getNumberPhone(),
                entity.getTariffIndex()
        );
    }

    @Override
    public ChangeSubscriberTariffEntity getDtoToEntity(ChangeSubscriberTariffDto dto) {
        return new ChangeSubscriberTariffEntity(
                dto.getNumberPhone(),
                dto.getTariffIndex()
        );
    }

    public ChangeSubscriberTariffEntity getCreateDtoToEntity(ChangeSubscriberTariffCreateDto createDto){
        return new ChangeSubscriberTariffEntity(
                createDto.getNumberPhone(),
                createDto.getTariffIndex()
        );
    }
}
