package com.application.billingsystem.mappers;

public interface Mapper <E, D>{

    D getEntityToDto(E entity);
    E getDtoToEntity(D dto);
}
