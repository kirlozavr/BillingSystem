package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.SubscriberReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberReportRepository
        extends JpaRepository<SubscriberReportEntity, Long> {

    Optional<SubscriberReportEntity> findFirstByNumberPhone(String numberPhone);
}
