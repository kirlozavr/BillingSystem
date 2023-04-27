package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.TariffCreateDto;
import com.application.billingsystem.dto.TariffDto;
import com.application.billingsystem.entity.TariffEntity;

public class TariffMapper implements Mapper<TariffEntity, TariffDto> {

    @Override
    public TariffDto getEntityToDto(TariffEntity entity) {
        return new TariffDto(
                entity.getId(),
                entity.getTariffIndex(),
                entity.getNameTariff(),
                entity.getNameOperator(),
                entity.getNameLocation(),
                entity.getMonetaryUnit(),
                entity.getTariffInfo()
        );
    }

    @Override
    public TariffEntity getDtoToEntity(TariffDto dto) {
        return new TariffEntity(
                dto.getTariffIndex(),
                dto.getNameTariff(),
                dto.getNameOperator(),
                dto.getNameLocation(),
                dto.getMonetaryUnit(),
                dto.getTariffInformation()
        );
    }

    public TariffEntity getCreateDtoToEntity(TariffCreateDto createDto){
        return new TariffEntity(
                createDto.getTariffIndex(),
                createDto.getNameTariff(),
                createDto.getNameOperator(),
                createDto.getNameLocation(),
                createDto.getMonetaryUnit(),
                createDto.getTariffInformation()
        );
    }
}
