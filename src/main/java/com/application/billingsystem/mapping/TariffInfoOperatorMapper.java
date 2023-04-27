package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.TariffInfoOperatorCreateDto;
import com.application.billingsystem.dto.TariffInfoOperatorDto;
import com.application.billingsystem.entity.TariffInfoOperatorEntity;

public class TariffInfoOperatorMapper
        implements Mapper<TariffInfoOperatorEntity, TariffInfoOperatorDto>{
    @Override
    public TariffInfoOperatorDto getEntityToDto(TariffInfoOperatorEntity entity) {
        return new TariffInfoOperatorDto(
                entity.getId(),
                entity.getOutBetAnotherOperator(),
                entity.getInBetAnotherOperator()
        );
    }

    @Override
    public TariffInfoOperatorEntity getDtoToEntity(TariffInfoOperatorDto dto) {
        return new TariffInfoOperatorEntity(
                dto.getOutBetAnotherOperator(),
                dto.getInBetAnotherOperator()
        );
    }

    public TariffInfoOperatorEntity getCreateDtoToEntity(TariffInfoOperatorCreateDto createDto){
        return new TariffInfoOperatorEntity(
                createDto.getOutBetAnotherOperator(),
                createDto.getInBetAnotherOperator()
        );
    }
}
