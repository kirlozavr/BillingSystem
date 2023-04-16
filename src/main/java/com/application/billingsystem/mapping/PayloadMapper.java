package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.PayloadCreateDto;
import com.application.billingsystem.dto.PayloadDto;
import com.application.billingsystem.entity.PayloadEntity;

public class PayloadMapper implements Mapper<PayloadEntity, PayloadDto> {
    @Override
    public PayloadDto getEntityToDto(PayloadEntity entity) {
        return new PayloadDto(
                entity.getId(),
                entity.getCallType(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getDuration(),
                entity.getCost()
        );
    }

    @Override
    public PayloadEntity getDtoToEntity(PayloadDto dto) {
        return new PayloadEntity(
                dto.getCallType(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getDuration(),
                dto.getCost()
        );
    }

    public PayloadEntity getCreateDtoToEntity(PayloadCreateDto createDto){
        return new PayloadEntity(
                createDto.getCallType(),
                createDto.getStartTime(),
                createDto.getEndTime(),
                createDto.getDuration(),
                createDto.getCost()
        );
    }
}
