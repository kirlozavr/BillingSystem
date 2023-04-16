package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.PayloadEntity;
import org.springframework.data.repository.CrudRepository;

public interface PayloadRepository extends CrudRepository<PayloadEntity, Long> {
}
