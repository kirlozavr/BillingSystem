package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.PayloadEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepository
        extends CrudRepository<PayloadEntity, Long> {
}
