package com.application.billingsystem.services;

import com.application.billingsystem.entity.SubscriberEntity;
import com.application.billingsystem.exceptions.IncorrectArgumentException;
import com.application.billingsystem.exceptions.SubscriberExistsException;
import com.application.billingsystem.exceptions.SubscriberNotFoundException;
import com.application.billingsystem.repositories.SubscriberRepository;
import com.application.billingsystem.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriberService {

    private final SubscriberRepository repository;

    @Autowired
    public SubscriberService(SubscriberRepository repository) {
        this.repository = repository;
    }

    public List<SubscriberEntity> getAll() {
        return repository.findAll();
    }

    public SubscriberEntity getById(Long id) {
        ValidationUtils.checkId(id);

        return repository.findById(id)
                .orElseThrow(() -> new SubscriberNotFoundException("Subscriber not found"));
    }

    public SubscriberEntity getByNumberPhone(String numberPhone) {
        ValidationUtils.checkNumberPhone(numberPhone);

        return repository.findFirstByNumberPhone(numberPhone)
                .orElseThrow(() -> new SubscriberNotFoundException("Subscriber not found"));
    }

    public SubscriberEntity getByNumberPhoneAndPositiveBalance(String numberPhone) {
        ValidationUtils.checkNumberPhone(numberPhone);

        return repository
                .findFirstByNumberPhoneAndBalanceGreaterThan(numberPhone, 0)
                .orElse(null);
    }

    @Transactional
    public void create(SubscriberEntity subscriber) {
        validate(subscriber);

        var exists = repository
                .existsByNumberPhone(subscriber.getNumberPhone());

        if (exists) {
            throw new SubscriberExistsException("Subscriber is exists");
        }

        repository.save(subscriber);
    }

    @Transactional
    public void update(SubscriberEntity subscriber) {
        validate(subscriber);

        if (!repository.existsById(subscriber.getId())) {
            throw new SubscriberNotFoundException("Subscriber not found");
        }

        repository.save(subscriber);
    }

    @Transactional
    public void delete(Long id) {
        ValidationUtils.checkId(id);

        if (!repository.existsById(id)) {
            throw new SubscriberNotFoundException("Subscriber is not exists");
        }

        repository.deleteById(id);
    }

    private void validate(SubscriberEntity entity) {
        if (entity.getNumberPhone() == null) {
            throw new IncorrectArgumentException("Номер телефона не указан");
        }
        if (entity.getTariffIndex() == null) {
            throw new IncorrectArgumentException("Индекс тарифа не указан");
        }
    }
}
