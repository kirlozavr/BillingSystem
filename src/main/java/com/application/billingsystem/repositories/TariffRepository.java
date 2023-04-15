package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.TariffEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TariffRepository
        extends CrudRepository<TariffEntity, Long> {
    Optional<TariffEntity> findTariffEntityByTariffIndex(String tariffIndex);
}
