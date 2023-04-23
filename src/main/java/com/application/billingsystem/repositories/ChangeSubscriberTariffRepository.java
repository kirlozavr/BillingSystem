package com.application.billingsystem.repositories;

import com.application.billingsystem.entity.ChangeSubscriberTariffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeSubscriberTariffRepository
        extends JpaRepository<ChangeSubscriberTariffEntity, Long> {

    List<ChangeSubscriberTariffEntity> findAllByNumberPhone(String numberPhone);
}
