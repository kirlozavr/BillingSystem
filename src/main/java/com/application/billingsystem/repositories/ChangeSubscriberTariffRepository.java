package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.ChangeSubscriberTariffEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeSubscriberTariffRepository
        extends CrudRepository<ChangeSubscriberTariffEntity, Long> {
}
