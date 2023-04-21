package com.application.billingsystem.services;

import com.application.billingsystem.entity.SubscriberReportEntity;
import com.application.billingsystem.exceptions.SubscriberReportNotFoundException;
import com.application.billingsystem.repositories.SubscriberReportRepository;
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

    public List<SubscriberReportEntity> getAllSubscriberReports(){
        return repository.findAll();
    }

    public SubscriberReportEntity getSubscriberReport(long subscriberReportId){
        return repository.findById(subscriberReportId)
                .orElseThrow(() -> new SubscriberReportNotFoundException("SubscriberReport not found"));
    }

    public SubscriberReportEntity getSubscriberReport(String numberPhone){
        return repository.findByNumberPhone(numberPhone)
                .orElseThrow(() -> new SubscriberReportNotFoundException("SubscriberReport not found"));
    }

    @Transactional
    public void createSubscriberReport(SubscriberReportEntity entity){
        repository.save(entity);
    }

    @Transactional
    public void updateSubscriberReport(SubscriberReportEntity entity){
        if(!repository.existsById(entity.getId())){
            throw new SubscriberReportNotFoundException("SubscriberReport not found");
        }
        repository.save(entity);
    }

    @Transactional
    public void deleteSubscriberReport(long subscriberReportId){
        if(!repository.existsById(subscriberReportId)){
            throw new SubscriberReportNotFoundException("SubscriberReport is not exists");
        }
    }
}
