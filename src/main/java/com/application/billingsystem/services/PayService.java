package com.application.billingsystem.services;

import com.application.billingsystem.entity.PayEntity;
import com.application.billingsystem.exceptions.PayNotFoundException;
import com.application.billingsystem.repositories.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayService {

    private final PayRepository repository;

    @Autowired
    public PayService(PayRepository repository) {
        this.repository = repository;
    }

    public Iterable<PayEntity> getAllPay() {
        return repository.findAll();
    }

    public PayEntity getPay(long payId) {
        return repository.findById(payId)
                .orElseThrow(() -> new PayNotFoundException("Pay not found"));
    }

    @Transactional
    public void createPay(PayEntity pay) {
        repository.save(pay);
    }

    @Transactional
    public void updatePay(PayEntity pay) {
        if (!repository.existsById(pay.getId())) {
            throw new PayNotFoundException("Pay not found");
        }
        repository.save(pay);
    }

    @Transactional
    public void deletePay(long payId) {
        var existsPay = repository.existsById(payId);
        if(!existsPay){
            throw new PayNotFoundException("Pay is not exists");
        }
        repository.deleteById(payId);
    }
}
