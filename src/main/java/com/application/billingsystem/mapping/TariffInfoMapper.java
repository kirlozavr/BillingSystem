package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.TariffInfoCreateDto;
import com.application.billingsystem.dto.TariffInfoDto;
import com.application.billingsystem.entity.TariffInfoEntity;

public class TariffInfoMapper
        implements Mapper<TariffInfoEntity, TariffInfoDto>{

    @Override
    public TariffInfoDto getEntityToDto(TariffInfoEntity entity) {
        return new TariffInfoDto(
                entity.getId(),
                entity.getMinuteLimit(),
                entity.getOutBetBeforeLimit(),
                entity.getOutBetAfterLimit(),
                entity.getInBetBeforeLimit(),
                entity.getInBetAfterLimit(),
                entity.getSubscriberPayment(),
                entity.getTariffInfoOperators(),
                entity.getTariffInfoLocations()
        );
    }

    @Override
    public TariffInfoEntity getDtoToEntity(TariffInfoDto dto) {
        return new TariffInfoEntity(
                dto.getMinuteLimit(),
                dto.getOutBetBeforeLimit(),
                dto.getOutBetAfterLimit(),
                dto.getInBetBeforeLimit(),
                dto.getInBetAfterLimit(),
                dto.getSubscriberPayment(),
                dto.getTariffInfoOperators(),
                dto.getTariffInfoLocations()
        );
    }

    public TariffInfoEntity getCreateDtoToEntity(TariffInfoCreateDto createDto){
        return new TariffInfoEntity(
                createDto.getMinuteLimit(),
                createDto.getOutBetBeforeLimit(),
                createDto.getOutBetAfterLimit(),
                createDto.getInBetBeforeLimit(),
                createDto.getInBetAfterLimit(),
                createDto.getSubscriberPayment(),
                createDto.getTariffInfoOperators(),
                createDto.getTariffInfoLocations()
        );
    }
}
