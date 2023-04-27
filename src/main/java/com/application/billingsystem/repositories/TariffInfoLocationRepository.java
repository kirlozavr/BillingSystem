package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.TariffInfoLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffInfoLocationRepository
        extends JpaRepository<TariffInfoLocationEntity, Long> {
}
