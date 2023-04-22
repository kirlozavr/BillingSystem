package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.SubscriberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberRepository
        extends CrudRepository<SubscriberEntity, Long> {

    Optional<SubscriberEntity> findFirstByNumberPhone(String numberPhone);
    Optional<SubscriberEntity> findFirstByNumberPhoneAndBalanceGreaterThan(String numberPhone, long balance);
}
