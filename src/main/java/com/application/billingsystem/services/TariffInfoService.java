package com.application.billingsystem.services;

import com.application.billingsystem.entity.TariffInfoEntity;
import com.application.billingsystem.exceptions.AlreadyExistsException;
import com.application.billingsystem.exceptions.NotFoundException;
import com.application.billingsystem.repositories.TariffInfoRepository;
import com.application.billingsystem.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffInfoService {

    private final TariffInfoRepository repository;

    @Autowired
    public TariffInfoService(TariffInfoRepository repository) {
        this.repository = repository;
    }

    public List<TariffInfoEntity> gelAll() {
        return repository.findAll();
    }

    public TariffInfoEntity getById(Long id) {
        ValidationUtils.checkId(id);

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tariff information not found"));
    }

    @Transactional
    public void create(TariffInfoEntity tariffInfo) {

        var exists = repository.existsById(tariffInfo.getId());

        if (exists) {
            throw new AlreadyExistsException("Tariff information is exists");
        }

        repository.save(tariffInfo);
    }

    @Transactional
    public void update(TariffInfoEntity tariffInfo) {

        if (!repository.existsById(tariffInfo.getId())) {
            throw new NotFoundException("Tariff information not found");
        }

        repository.save(tariffInfo);
    }

    @Transactional
    public void delete(Long id) {
        ValidationUtils.checkId(id);

        if (!repository.existsById(id)) {
            throw new NotFoundException("Tariff information is not exists");
        }

        repository.deleteById(id);
    }

}
