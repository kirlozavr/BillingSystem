package com.application.billingsystem.mapping;

import com.application.billingsystem.dto.PaymentCreateDto;
import com.application.billingsystem.dto.PaymentDto;
import com.application.billingsystem.entity.PaymentEntity;

public class PaymentMapper implements Mapper<PaymentEntity, PaymentDto>{

    @Override
    public PaymentDto getEntityToDto(PaymentEntity entity) {
        return new PaymentDto(
                entity.getId(),
                entity.getNumberPhone(),
                entity.getMoney()
        );
    }

    @Override
    public PaymentEntity getDtoToEntity(PaymentDto dto) {
        return new PaymentEntity(
                dto.getNumberPhone(),
                dto.getMoney()
        );
    }

    public PaymentEntity getCreateDtoToEntity(PaymentCreateDto createDto) {
        return new PaymentEntity(
                createDto.getNumberPhone(),
                createDto.getMoney()
        );
    }
}
