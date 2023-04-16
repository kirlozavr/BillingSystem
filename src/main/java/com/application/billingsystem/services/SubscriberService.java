package com.application.billingsystem.services;

import com.application.billingsystem.entity.SubscriberEntity;
import com.application.billingsystem.exceptions.SubscriberNotFoundException;
import com.application.billingsystem.repositories.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriberService {

    private final SubscriberRepository repository;

    @Autowired
    public SubscriberService(SubscriberRepository repository) {
        this.repository = repository;
    }

    public Iterable<SubscriberEntity> getAllSubscribers(){
        return repository.findAll();
    }

    public SubscriberEntity getSubscriber(Long subscriberId){
        return repository.findById(subscriberId)
                .orElseThrow(() -> new SubscriberNotFoundException("Subscriber not found"));
    }

    public SubscriberEntity getSubscriber(String numberPhone){
        return repository.findSubscriberEntityByNumberPhone(numberPhone)
                .orElseThrow(() -> new SubscriberNotFoundException("Subscriber not found"));
    }

    @Transactional
    public void createSubscriber(SubscriberEntity subscriber){
        var subscriberOptional = repository
                .findSubscriberEntityByNumberPhone(subscriber.getNumberPhone());
        if(subscriberOptional.isPresent()){
            throw new SubscriberNotFoundException("Subscriber is exists");
        }
        repository.save(subscriber);
    }

    @Transactional
    public void updateSubscriber(SubscriberEntity subscriber){
        if (!repository.existsById(subscriber.getId())) {
            throw new SubscriberNotFoundException("Subscriber not found");
        }
        repository.save(subscriber);
    }

    @Transactional
    public void deleteSubscriber(Long subscriberId){
        var existsSubscriber = repository.existsById(subscriberId);
        if(!existsSubscriber){
            throw new SubscriberNotFoundException("Subscriber is not exists");
        }
        repository.deleteById(subscriberId);
    }
}
