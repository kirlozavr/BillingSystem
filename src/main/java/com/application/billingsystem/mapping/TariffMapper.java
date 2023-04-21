package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.TariffCreateDto;
import com.application.billingsystem.dto.TariffDto;
import com.application.billingsystem.entity.TariffEntity;
import com.application.billingsystem.mapping.Mapper;

public class TariffMapper implements Mapper<TariffEntity, TariffDto> {

    @Override
    public TariffDto getEntityToDto(TariffEntity entity) {
        return new TariffDto(
                entity.getId(),
                entity.getTariffIndex(),
                entity.getNameTariff(),
                entity.getMinuteLimit(),
                entity.getOutBetBeforeLimit(),
                entity.getOutBetAfterLimit(),
                entity.getInBetBeforeLimit(),
                entity.getInBetAfterLimit(),
                entity.getSubscriberPayment()
        );
    }

    @Override
    public TariffEntity getDtoToEntity(TariffDto dto) {
        return new TariffEntity(
                dto.getTariffIndex(),
                dto.getNameTariff(),
                dto.getMinuteLimit(),
                dto.getOutBetBeforeLimit(),
                dto.getOutBetAfterLimit(),
                dto.getInBetBeforeLimit(),
                dto.getInBetAfterLimit(),
                dto.getSubscriberPayment(),
                null
        );
    }

    public TariffEntity getCreateDtoToEntity(TariffCreateDto createDto){
        return new TariffEntity(
                createDto.getTariffIndex(),
                createDto.getNameTariff(),
                createDto.getMinuteLimit(),
                createDto.getOutBetBeforeLimit(),
                createDto.getOutBetAfterLimit(),
                createDto.getInBetBeforeLimit(),
                createDto.getInBetAfterLimit(),
                createDto.getSubscriberPayment(),
                createDto.getMonetaryUnit()
        );
    }
}
