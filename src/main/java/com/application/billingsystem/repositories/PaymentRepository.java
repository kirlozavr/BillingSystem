package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository
        extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findAllByNumberPhone(String numberPhone);
}
