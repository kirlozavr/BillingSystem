package com.application.billingsystem.services;

import com.application.billingsystem.entity.TariffInfoOperatorEntity;
import com.application.billingsystem.exceptions.AlreadyExistsException;
import com.application.billingsystem.exceptions.NotFoundException;
import com.application.billingsystem.repositories.TariffInfoOperatorRepository;
import com.application.billingsystem.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffInfoOperatorService {

    private final TariffInfoOperatorRepository repository;

    @Autowired
    public TariffInfoOperatorService(TariffInfoOperatorRepository repository) {
        this.repository = repository;
    }

    public List<TariffInfoOperatorEntity> gelAll() {
        return repository.findAll();
    }

    public TariffInfoOperatorEntity getById(Long id) {
        ValidationUtils.checkId(id);

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tariff information about operator not found"));
    }

    @Transactional
    public void create(TariffInfoOperatorEntity tariffInfoOperator) {

        var exists = repository.existsById(tariffInfoOperator.getId());

        if (exists) {
            throw new AlreadyExistsException("Tariff information about operator is exists");
        }

        repository.save(tariffInfoOperator);
    }

    @Transactional
    public void update(TariffInfoOperatorEntity tariffInfoOperator) {

        if (!repository.existsById(tariffInfoOperator.getId())) {
            throw new NotFoundException("Tariff information about operator not found");
        }

        repository.save(tariffInfoOperator);
    }

    @Transactional
    public void delete(Long id) {
        ValidationUtils.checkId(id);

        if (!repository.existsById(id)) {
            throw new NotFoundException("Tariff information about operator is not exists");
        }

        repository.deleteById(id);
    }
}
