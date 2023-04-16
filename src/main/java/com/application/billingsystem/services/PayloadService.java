package com.application.billingsystem.services;

import com.application.billingsystem.entity.PayloadEntity;
import com.application.billingsystem.exceptions.PayloadNotFoundException;
import com.application.billingsystem.repositories.PayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayloadService {

    private final PayloadRepository repository;

    @Autowired
    public PayloadService(PayloadRepository repository) {
        this.repository = repository;
    }

    public Iterable<PayloadEntity> getAllPayloads() {
        return repository.findAll();
    }

    public PayloadEntity getPayload(long payloadId) {
        return repository.findById(payloadId)
                .orElseThrow(() -> new PayloadNotFoundException("Payload not found"));
    }

    @Transactional
    public void createPayload(PayloadEntity payload) {
        repository.save(payload);
    }

    @Transactional
    public void updatePayload(PayloadEntity payload) {
        if (!repository.existsById(payload.getId())) {
            throw new PayloadNotFoundException("Payload not found");
        }
        repository.save(payload);
    }

    @Transactional
    public void deletePayload(long payloadId) {
        var existsPayload = repository.existsById(payloadId);
        if(!existsPayload){
            throw new PayloadNotFoundException("Payload is not exists");
        }
        repository.deleteById(payloadId);
    }
}
