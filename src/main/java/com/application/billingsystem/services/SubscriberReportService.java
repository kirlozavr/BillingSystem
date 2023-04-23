package com.application.billingsystem.services;

import com.application.billingsystem.entity.SubscriberReportEntity;
import com.application.billingsystem.exceptions.IncorrectArgumentException;
import com.application.billingsystem.exceptions.SubscriberReportNotFoundException;
import com.application.billingsystem.repositories.SubscriberReportRepository;
import com.application.billingsystem.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriberReportService {

    private final SubscriberReportRepository repository;

    @Autowired
    public SubscriberReportService(SubscriberReportRepository repository) {
        this.repository = repository;
    }

    public List<SubscriberReportEntity> getAll() {
        return repository.findAll();
    }

    public List<SubscriberReportEntity> getAllByNumberPhone(String numberPhone) {
        ValidationUtils.checkNumberPhone(numberPhone);
        return repository.findAllByNumberPhone(numberPhone);
    }

    public SubscriberReportEntity getById(long id) {
        ValidationUtils.checkId(id);

        return repository.findById(id)
                .orElseThrow(() -> new SubscriberReportNotFoundException("SubscriberReport not found"));
    }

    @Transactional
    public void create(SubscriberReportEntity subscriberReport) {
        validate(subscriberReport);
        repository.save(subscriberReport);
    }

    @Transactional
    public void update(SubscriberReportEntity subscriberReport) {
        validate(subscriberReport);

        if (!repository.existsById(subscriberReport.getId())) {
            throw new SubscriberReportNotFoundException("SubscriberReport not found");
        }

        repository.save(subscriberReport);
    }

    @Transactional
    public void delete(long id) {
        ValidationUtils.checkId(id);

        if (!repository.existsById(id)) {
            throw new SubscriberReportNotFoundException("SubscriberReport is not exists");
        }

        repository.deleteById(id);
    }

    private void validate(SubscriberReportEntity entity) {
        if (entity.getNumberPhone() == null) {
            throw new IncorrectArgumentException("Номер телефона не указан");
        }
        if (entity.getTariffIndex() == null) {
            throw new IncorrectArgumentException("Индекс тарифа не указан");
        }
        if (entity.getPayloads() == null) {
            throw new IncorrectArgumentException("Звонки не указаны");
        }
        if (entity.getPayloads().size() == 0) {
            throw new IncorrectArgumentException("Звонки не указаны");
        }
        if (entity.getMonetaryUnit() == null) {
            throw new IncorrectArgumentException("Денежная единица не указана");
        }
    }
}
