package com.application.billingsystem.services;

import com.application.billingsystem.entity.ChangeSubscriberTariffEntity;
import com.application.billingsystem.exceptions.ChangeSubscriberTariffNotFoundException;
import com.application.billingsystem.repositories.ChangeSubscriberTariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangeSubscriberTariffService {

    private final ChangeSubscriberTariffRepository repository;

    @Autowired
    public ChangeSubscriberTariffService(ChangeSubscriberTariffRepository repository) {
        this.repository = repository;
    }

    public Iterable<ChangeSubscriberTariffEntity> getAllChangeSubscriberTariffs() {
        return repository.findAll();
    }

    public ChangeSubscriberTariffEntity getChangeSubscriberTariff(long changeSubscriberTariffId) {
        return repository.findById(changeSubscriberTariffId)
                .orElseThrow(() -> new ChangeSubscriberTariffNotFoundException("ChangeSubscriberTariffNotFoundException not found"));
    }

    @Transactional
    public void createChangeSubscriberTariff(ChangeSubscriberTariffEntity changeSubscriberTariff) {
        repository.save(changeSubscriberTariff);
    }

    @Transactional
    public void updateChangeSubscriberTariff(ChangeSubscriberTariffEntity changeSubscriberTariff) {
        if (!repository.existsById(changeSubscriberTariff.getId())) {
            throw new ChangeSubscriberTariffNotFoundException("ChangeSubscriberTariff not found");
        }
        repository.save(changeSubscriberTariff);
    }

    @Transactional
    public void deleteChangeSubscriberTariff(long changeSubscriberTariffId) {
        var existsChangeSubscriberTariff = repository.existsById(changeSubscriberTariffId);
        if (!existsChangeSubscriberTariff) {
            throw new ChangeSubscriberTariffNotFoundException("ChangeSubscriberTariff is not exists");
        }
        repository.deleteById(changeSubscriberTariffId);
    }
}
