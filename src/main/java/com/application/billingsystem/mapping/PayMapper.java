package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.PayCreateDto;
import com.application.billingsystem.dto.PayDto;
import com.application.billingsystem.entity.PayEntity;

public class PayMapper implements Mapper<PayEntity, PayDto>{

    @Override
    public PayDto getEntityToDto(PayEntity entity) {
        return new PayDto(
                entity.getId(),
                entity.getNumberPhone(),
                entity.getMoney()
        );
    }

    @Override
    public PayEntity getDtoToEntity(PayDto dto) {
        return new PayEntity(
                dto.getNumberPhone(),
                dto.getMoney()
        );
    }

    public PayEntity getCreateDtoToEntity(PayCreateDto createDto) {
        return new PayEntity(
                createDto.getNumberPhone(),
                createDto.getMoney()
        );
    }
}
