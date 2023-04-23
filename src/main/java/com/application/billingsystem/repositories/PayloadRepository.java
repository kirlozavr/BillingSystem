package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.PayloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepository
        extends JpaRepository<PayloadEntity, Long> {
}
