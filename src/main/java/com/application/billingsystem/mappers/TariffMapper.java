package com.application.billingsystem.mappers;

import com.application.billingsystem.dto.TariffCreateDto;
import com.application.billingsystem.dto.TariffDto;
import com.application.billingsystem.entity.TariffEntity;

public class TariffMapper implements Mapper<TariffEntity, TariffDto>{

    @Override
    public TariffDto getEntityToDto(TariffEntity entity) {
        return new TariffDto(
                entity.getId(),
                entity.getTariffIndex(),
                entity.getNameTariff(),
                entity.getMinuteLimit(),
                entity.getOutBet(),
                entity.getOutBetAfterLimit(),
                entity.getInBet(),
                entity.getSubscriberPayment()
        );
    }

    @Override
    public TariffEntity getDtoToEntity(TariffDto dto) {
        return new TariffEntity(
                dto.getTariffIndex(),
                dto.getNameTariff(),
                dto.getMinuteLimit(),
                dto.getOutBet(),
                dto.getOutBetAfterLimit(),
                dto.getInBet(),
                dto.getSubscriberPayment(),
                null
        );
    }

    public TariffEntity getCreateDtoToEntity(TariffCreateDto createDto){
        return new TariffEntity(
                createDto.getTariffIndex(),
                createDto.getNameTariff(),
                createDto.getMinuteLimit(),
                createDto.getOutBet(),
                createDto.getOutBetAfterLimit(),
                createDto.getInBet(),
                createDto.getSubscriberPayment(),
                createDto.getMonetaryUnit()
        );
    }
}
