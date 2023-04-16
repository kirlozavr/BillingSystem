package com.application.billingsystem.controllers;

import com.application.billingsystem.FloatCompare;
import com.application.billingsystem.dto.SubscriberCreateDto;
import com.application.billingsystem.dto.SubscriberDto;
import com.application.billingsystem.entity.SubscriberEntity;
import com.application.billingsystem.mappers.SubscriberMapper;
import com.application.billingsystem.services.SubscriberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    private final SubscriberService service;
    private final SubscriberMapper mapper = new SubscriberMapper();

    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<SubscriberDto> getAllSubscribers(){
        return StreamSupport
                .stream(service.getAllSubscribers().spliterator(), false)
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping(path = "/id={subscriberId}")
    public SubscriberDto findById(@PathVariable("subscriberId") Long subscriberId){
        return mapper
                .getEntityToDto(service.getSubscriber(subscriberId));
    }

    @GetMapping(path = "/numer_phone={numberPhone}")
    public SubscriberDto findByNumberPhone(@PathVariable("numberPhone") String numberPhone){
        return mapper
                .getEntityToDto(service.getSubscriber(numberPhone));
    }

    @PostMapping
    public void postSubscriber(@RequestBody SubscriberCreateDto createDto){
        service.setNewSubscriber(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping(path = "/{subscriberId}")
    public void putSubscriber(
            @PathVariable("subscriberId") Long subscriberId,
            @RequestBody SubscriberCreateDto createDto
    ){
        SubscriberEntity subscriberEntity = service.getSubscriber(subscriberId);

        if(createDto.getNumberPhone() != null &&
                createDto.getNumberPhone().length() >0 &&
                !createDto.getNumberPhone().equals(subscriberEntity.getNumberPhone()) &&
                service.getSubscriber(createDto.getNumberPhone()) == null){
            subscriberEntity.setNumberPhone(createDto.getNumberPhone());
        }
        if(createDto.getTariffIndex() != null &&
                createDto.getTariffIndex().length() >0 &&
                !createDto.getTariffIndex().equals(subscriberEntity.getTariffIndex())){
            subscriberEntity.setTariffIndex(createDto.getTariffIndex());
        }
        if(createDto.getBalance() > 0.0f &&
                !FloatCompare.isEquals(createDto.getBalance(), subscriberEntity.getBalance())){
            subscriberEntity.setBalance(createDto.getBalance());
        }

        service.updateSubscriber(subscriberEntity);
    }

    @DeleteMapping(path = "/{subscriberId}")
    public void deleteSubscriber(@PathVariable("subscriberId") Long subscriberId){
        service.deleteSubscriber(subscriberId);
    }
}
