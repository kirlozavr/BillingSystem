package com.application.billingsystem.mappers;

import com.application.billingsystem.dto.SubscriberCreateDto;
import com.application.billingsystem.dto.SubscriberDto;
import com.application.billingsystem.entity.SubscriberEntity;

public class SubscriberMapper implements Mapper<SubscriberEntity, SubscriberDto>{


    @Override
    public SubscriberDto getEntityToDto(SubscriberEntity entity) {
        return new SubscriberDto(
                entity.getId(),
                entity.getNumberPhone(),
                entity.getTariffIndex(),
                entity.getBalance()
        );
    }

    @Override
    public SubscriberEntity getDtoToEntity(SubscriberDto dto) {
        return new SubscriberEntity(
                dto.getNumberPhone(),
                dto.getTariffIndex(),
                dto.getBalance()
        );
    }

    public SubscriberEntity getCreateDtoToEntity(SubscriberCreateDto createDto){
        return new SubscriberEntity(
                createDto.getNumberPhone(),
                createDto.getTariffIndex(),
                createDto.getBalance()
        );
    }
}
