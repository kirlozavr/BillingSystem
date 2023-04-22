package com.application.billingsystem.services;

import com.application.billingsystem.entity.PaymentEntity;
import com.application.billingsystem.exceptions.PaymentNotFoundException;
import com.application.billingsystem.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Iterable<PaymentEntity> getAllPayments() {
        return repository.findAll();
    }

    public PaymentEntity getPayment(long payId) {
        return repository.findById(payId)
                .orElseThrow(() -> new PaymentNotFoundException("Pay not found"));
    }

    @Transactional
    public void createPayment(PaymentEntity pay) {
        repository.save(pay);
    }

    @Transactional
    public void updatePayment(PaymentEntity pay) {
        if (!repository.existsById(pay.getId())) {
            throw new PaymentNotFoundException("Pay not found");
        }
        repository.save(pay);
    }

    @Transactional
    public void deletePayment(long payId) {
        if (!repository.existsById(payId)) {
            throw new PaymentNotFoundException("Pay is not exists");
        }
        repository.deleteById(payId);
    }
}
