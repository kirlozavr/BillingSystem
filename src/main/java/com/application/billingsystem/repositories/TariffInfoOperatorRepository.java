package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.TariffInfoOperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffInfoOperatorRepository
        extends JpaRepository<TariffInfoOperatorEntity, Long> {
}
