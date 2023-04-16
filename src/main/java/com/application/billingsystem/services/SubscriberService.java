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
                .orElseThrow(() -> new SubscriberNotFoundException("Абонент не найден"));
    }

    public SubscriberEntity getSubscriber(String numberPhone){
        return repository.findSubscriberEntityByNumberPhone(numberPhone)
                .orElseThrow(() -> new SubscriberNotFoundException("Абонент не найден"));
    }

    public void setNewSubscriber(SubscriberEntity subscriber){
        var subscriberOptional = repository
                .findSubscriberEntityByNumberPhone(subscriber.getNumberPhone());
        if(subscriberOptional.isPresent()){
            throw new SubscriberNotFoundException("Абонент сущесвует");
        }
        repository.save(subscriber);
    }

    @Transactional
    public void updateSubscriber(SubscriberEntity subscriber){
        repository.save(subscriber);
    }

    public void deleteSubscriber(Long subscriberId){
        var existsSubscriber = repository.existsById(subscriberId);
        if(!existsSubscriber){
            throw new SubscriberNotFoundException("Абонента не существует");
        }
        repository.deleteById(subscriberId);
    }
}
