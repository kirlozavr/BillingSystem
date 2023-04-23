package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.SubscriberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberRepository
        extends JpaRepository<SubscriberEntity, Long> {

    boolean existsByNumberPhone(String numberPhone);
    Optional<SubscriberEntity> findFirstByNumberPhone(String numberPhone);
    Optional<SubscriberEntity> findFirstByNumberPhoneAndBalanceGreaterThan(String numberPhone, long balance);
}
