package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository
        extends CrudRepository<PaymentEntity, Long> {
}
