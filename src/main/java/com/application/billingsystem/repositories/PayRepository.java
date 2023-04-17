package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.PayEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository
        extends CrudRepository<PayEntity, Long> {
}
