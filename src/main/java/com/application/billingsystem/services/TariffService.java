package com.application.billingsystem.services;

import com.application.billingsystem.entity.TariffEntity;
import com.application.billingsystem.exceptions.AlreadyExistsException;
import com.application.billingsystem.exceptions.IncorrectArgumentException;
import com.application.billingsystem.exceptions.NotFoundException;
import com.application.billingsystem.repositories.TariffRepository;
import com.application.billingsystem.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffService {

    private final TariffRepository repository;

    @Autowired
    public TariffService(TariffRepository repository) {
        this.repository = repository;
    }

    public List<TariffEntity> gelAll() {
        return repository.findAll();
    }

    public TariffEntity getById(Long id) {
        ValidationUtils.checkId(id);

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tariff not found"));
    }

    public TariffEntity getByIndex(String tariffIndex) {
        ValidationUtils.checkIndex(tariffIndex);

        return repository.findFirstByTariffIndex(tariffIndex)
                .orElseThrow(() -> new NotFoundException("Tariff not found"));
    }

    @Transactional
    public void create(TariffEntity tariff) {
        validate(tariff);

        var exists = repository.existsByTariffIndex(tariff.getTariffIndex());

        if (exists) {
            throw new AlreadyExistsException("Tariff is exists");
        }

        repository.save(tariff);
    }

    @Transactional
    public void update(TariffEntity tariff) {
        validate(tariff);

        if (!repository.existsById(tariff.getId())) {
            throw new NotFoundException("Tariff not found");
        }

        repository.save(tariff);
    }

    @Transactional
    public void delete(Long id) {
        ValidationUtils.checkId(id);

        if (!repository.existsById(id)) {
            throw new NotFoundException("Tariff is not exists");
        }

        repository.deleteById(id);
    }

    private void validate(TariffEntity entity) {
        if (entity.getNameTariff() == null) {
            throw new IncorrectArgumentException("Название тарифа не указано");
        }
        if (entity.getTariffIndex() == null) {
            throw new IncorrectArgumentException("Индекс тарифа не указан");
        }
        if (entity.getNameOperator() == null) {
            throw new IncorrectArgumentException("Название оператора не указано");
        }
        if (entity.getNameLocation() == null) {
            throw new IncorrectArgumentException("Название локации для тарифа не указано");
        }
        if (entity.getMonetaryUnit() == null) {
            throw new IncorrectArgumentException("Денежная единица не указана");
        }
        if (entity.getTariffInfo() == null) {
            throw new IncorrectArgumentException("Информация о стоимости услуг не указана");
        }
    }
}
