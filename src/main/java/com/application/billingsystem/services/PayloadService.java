package com.application.billingsystem.services;

import com.application.billingsystem.entity.PayloadEntity;
import com.application.billingsystem.exceptions.IncorrectArgumentException;
import com.application.billingsystem.exceptions.PayloadNotFoundException;
import com.application.billingsystem.repositories.PayloadRepository;
import com.application.billingsystem.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PayloadService {

    private final PayloadRepository repository;

    @Autowired
    public PayloadService(PayloadRepository repository) {
        this.repository = repository;
    }

    public List<PayloadEntity> getAll() {
        return repository.findAll();
    }

    public PayloadEntity getById(long id) {
        ValidationUtils.checkId(id);

        return repository.findById(id)
                .orElseThrow(() -> new PayloadNotFoundException("Payload not found"));
    }

    @Transactional
    public void create(PayloadEntity payload) {
        validate(payload);
        repository.save(payload);
    }

    @Transactional
    public void update(PayloadEntity payload) {
        validate(payload);

        if (!repository.existsById(payload.getId())) {
            throw new PayloadNotFoundException("Payload not found");
        }

        repository.save(payload);
    }

    @Transactional
    public void delete(long id) {
        ValidationUtils.checkId(id);

        if (!repository.existsById(id)) {
            throw new PayloadNotFoundException("Payload is not exists");
        }

        repository.deleteById(id);
    }

    private void validate(PayloadEntity entity) {
        if (entity.getCallType() == null) {
            throw new IncorrectArgumentException("Тип звонка не указан");
        }
        if (entity.getStartTime() == null) {
            throw new IncorrectArgumentException("Дата начала звонка не указана");
        }
        if (entity.getEndTime() == null) {
            throw new IncorrectArgumentException("Дата конца звонка не указана");
        }
        if (entity.getDuration() == null) {
            throw new IncorrectArgumentException("Продолжительность звонка не указана");
        }
    }
}
