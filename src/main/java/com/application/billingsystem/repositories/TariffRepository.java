package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.TariffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TariffRepository
        extends JpaRepository<TariffEntity, Long> {
    boolean existsByTariffIndex(String tariffIndex);
    Optional<TariffEntity> findFirstByTariffIndex(String tariffIndex);
}
