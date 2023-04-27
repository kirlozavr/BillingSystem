package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.TariffInfoLocationCreateDto;
import com.application.billingsystem.dto.TariffInfoLocationDto;
import com.application.billingsystem.entity.TariffInfoLocationEntity;

public class TariffInfoLocationMapper
        implements Mapper<TariffInfoLocationEntity, TariffInfoLocationDto>{
    @Override
    public TariffInfoLocationDto getEntityToDto(TariffInfoLocationEntity entity) {
        return new TariffInfoLocationDto(
                entity.getId(),
                entity.getTargetNameLocation(),
                entity.getOutBetAnotherLocation(),
                entity.getInBetAnotherLocation()
        );
    }

    @Override
    public TariffInfoLocationEntity getDtoToEntity(TariffInfoLocationDto dto) {
        return new TariffInfoLocationEntity(
                dto.getTargetNameLocation(),
                dto.getOutBetAnotherLocation(),
                dto.getInBetAnotherLocation()
        );
    }

    public TariffInfoLocationEntity getCreateDtoToEntity(TariffInfoLocationCreateDto createDto){
        return new TariffInfoLocationEntity(
                createDto.getTargetNameLocation(),
                createDto.getOutBetAnotherLocation(),
                createDto.getInBetAnotherLocation()
        );
    }
}
