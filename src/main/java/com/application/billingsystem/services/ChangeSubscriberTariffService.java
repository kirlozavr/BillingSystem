package com.application.billingsystem.services;

import com.application.billingsystem.entity.ChangeSubscriberTariffEntity;
import com.application.billingsystem.exceptions.ChangeSubscriberTariffNotFoundException;
import com.application.billingsystem.exceptions.IncorrectArgumentException;
import com.application.billingsystem.repositories.ChangeSubscriberTariffRepository;
import com.application.billingsystem.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChangeSubscriberTariffService {

    private final ChangeSubscriberTariffRepository repository;

    @Autowired
    public ChangeSubscriberTariffService(ChangeSubscriberTariffRepository repository) {
        this.repository = repository;
    }

    public List<ChangeSubscriberTariffEntity> getAll() {
        return repository.findAll();
    }

    public List<ChangeSubscriberTariffEntity> getAllByNumberPhone(String numberPhone) {
        ValidationUtils.checkNumberPhone(numberPhone);
        return repository.findAllByNumberPhone(numberPhone);
    }

    public ChangeSubscriberTariffEntity getById(long id) {
        ValidationUtils.checkId(id);

        return repository.findById(id)
                .orElseThrow(() -> new ChangeSubscriberTariffNotFoundException("changeSubscriberTariff not found"));
    }

    @Transactional
    public void create(ChangeSubscriberTariffEntity changeSubscriberTariff) {
        validate(changeSubscriberTariff);
        repository.save(changeSubscriberTariff);
    }

    @Transactional
    public void update(ChangeSubscriberTariffEntity changeSubscriberTariff) {
        validate(changeSubscriberTariff);

        if (!repository.existsById(changeSubscriberTariff.getId())) {
            throw new ChangeSubscriberTariffNotFoundException("ChangeSubscriberTariff not found");
        }

        repository.save(changeSubscriberTariff);
    }

    @Transactional
    public void delete(long id) {
        ValidationUtils.checkId(id);

        if (!repository.existsById(id)) {
            throw new ChangeSubscriberTariffNotFoundException("ChangeSubscriberTariff is not exists");
        }

        repository.deleteById(id);
    }

    private void validate(ChangeSubscriberTariffEntity entity) {
        if (entity.getTariffIndex() == null) {
            throw new IncorrectArgumentException("Индекс тарифа не указан");
        }
        if (entity.getNumberPhone() == null) {
            throw new IncorrectArgumentException("Номер телефона не указан");
        }
    }
}
