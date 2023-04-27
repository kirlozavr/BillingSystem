package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.TariffInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffInfoRepository
        extends JpaRepository<TariffInfoEntity, Long> {
}
