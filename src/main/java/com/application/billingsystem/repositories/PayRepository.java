package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.PayEntity;
import org.springframework.data.repository.CrudRepository;

public interface PayRepository extends CrudRepository<PayEntity, Long> {
}
