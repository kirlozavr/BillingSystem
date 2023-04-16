package com.application.billingsystem.mapping;

public interface Mapper <E, D>{

    D getEntityToDto(E entity);
    E getDtoToEntity(D dto);
}
