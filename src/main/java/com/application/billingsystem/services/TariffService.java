package com.application.billingsystem.services;

import com.application.billingsystem.entity.TariffEntity;
import com.application.billingsystem.exceptions.TariffNotFoundException;
import com.application.billingsystem.repositories.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TariffService {

    private final TariffRepository repository;

    @Autowired
    public TariffService(TariffRepository repository) {
        this.repository = repository;
    }

    public Iterable<TariffEntity> gelAllTariff(){
        return repository.findAll();
    }

    public TariffEntity getTariff(Long tariffId){
        return repository.findById(tariffId)
                .orElseThrow(() -> new TariffNotFoundException("Тариф не найден"));
    }

    public TariffEntity getTariff(String tariffIndex){
        return repository.findTariffEntityByTariffIndex(tariffIndex)
                .orElseThrow(() -> new TariffNotFoundException("Тариф не найден"));
    }

    public void setNewTariff(TariffEntity tariff){
        var tariffOptional = repository
                .findTariffEntityByTariffIndex(tariff.getTariffIndex());
        if (tariffOptional.isPresent()){
            throw new TariffNotFoundException("Тариф существует");
        }
        repository.save(tariff);
    }

    @Transactional
    public void updateTariff(TariffEntity tariff){
        repository.save(tariff);
    }

    public void deleteTariff(Long tariffId){
        var existsTariff = repository.existsById(tariffId);
        if(!existsTariff){
            throw new TariffNotFoundException("Тариф не существует");
        }
        repository.deleteById(tariffId);
    }
}
