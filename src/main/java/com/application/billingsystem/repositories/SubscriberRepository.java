package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.SubscriberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriberRepository extends CrudRepository<SubscriberEntity, Long> {

    Optional<SubscriberEntity> findSubscriberEntityByNumberPhone(String numberPhone);
}
