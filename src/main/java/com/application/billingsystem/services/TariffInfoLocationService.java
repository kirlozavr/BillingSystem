package com.application.billingsystem.services;

import com.application.billingsystem.entity.TariffEntity;
import com.application.billingsystem.entity.TariffInfoLocationEntity;
import com.application.billingsystem.exceptions.AlreadyExistsException;
import com.application.billingsystem.exceptions.IncorrectArgumentException;
import com.application.billingsystem.exceptions.NotFoundException;
import com.application.billingsystem.repositories.TariffInfoLocationRepository;
import com.application.billingsystem.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffInfoLocationService {

    private final TariffInfoLocationRepository repository;

    @Autowired
    public TariffInfoLocationService(TariffInfoLocationRepository repository) {
        this.repository = repository;
    }

    public List<TariffInfoLocationEntity> gelAll() {
        return repository.findAll();
    }

    public TariffInfoLocationEntity getById(Long id) {
        ValidationUtils.checkId(id);

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tariff information about location not found"));
    }

    @Transactional
    public void create(TariffInfoLocationEntity tariffInfoLocation) {
        validate(tariffInfoLocation);

        var exists = repository.existsById(tariffInfoLocation.getId());

        if (exists) {
            throw new AlreadyExistsException("Tariff information about location is exists");
        }

        repository.save(tariffInfoLocation);
    }

    @Transactional
    public void update(TariffInfoLocationEntity tariffInfoLocation) {
        validate(tariffInfoLocation);

        if (!repository.existsById(tariffInfoLocation.getId())) {
            throw new NotFoundException("Tariff information about location not found");
        }

        repository.save(tariffInfoLocation);
    }

    @Transactional
    public void delete(Long id) {
        ValidationUtils.checkId(id);

        if (!repository.existsById(id)) {
            throw new NotFoundException("Tariff information about location is not exists");
        }

        repository.deleteById(id);
    }

    private void validate(TariffInfoLocationEntity entity) {
        if (entity.getTargetNameLocation() == null) {
            throw new IncorrectArgumentException("Название локации для которой будут особые условия не указано");
        }
    }
}
