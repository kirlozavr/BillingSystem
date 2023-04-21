package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.SubscriberReportEntity;
import com.application.billingsystem.main.FloatCompare;
import com.application.billingsystem.mapping.SubscriberReportMapper;
import com.application.billingsystem.services.SubscriberReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/report")
public class SubscriberReportController {

    private final SubscriberReportService service;
    private final SubscriberReportMapper mapper = new SubscriberReportMapper();

    @Autowired
    public SubscriberReportController(SubscriberReportService service) {
        this.service = service;
    }

    @GetMapping(path = "/")
    public List<SubscriberReportDto> getAllReports(){
        return StreamSupport
                .stream(service.getAllSubscriberReports().spliterator(), false)
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping(path = "/id={subscriberReportId}")
    public SubscriberReportDto findById(@PathVariable("subscriberReportId") Long subscriberReportId){
        return mapper
                .getEntityToDto(service.getSubscriberReport(subscriberReportId));
    }

    @GetMapping(path = "/number_phone={numberPhone}")
    public SubscriberReportDto findByNumberPhone(@PathVariable("numberPhone") String numberPhone){
        return mapper
                .getEntityToDto(service.getSubscriberReport(numberPhone));
    }

    @PostMapping
    public void postSubscriberReport(@RequestBody SubscriberReportDto subscriberReportDto){
        service.createSubscriberReport(mapper.getDtoToEntity(subscriberReportDto));
    }

    @PutMapping(path = "/{subscriberReportId}")
    public void putSubscriberReport(
            @PathVariable("subscriberReportId") Long subscriberReportId,
            @RequestBody SubscriberReportDto subscriberReportDto
    ){
        SubscriberReportEntity subscriberReportEntity = service.getSubscriberReport(subscriberReportId);

        if(subscriberReportDto.getTariffIndex() != null &&
                subscriberReportDto.getTariffIndex().length() >0 &&
                !subscriberReportDto.getTariffIndex().equals(subscriberReportEntity.getTariffIndex())
        ){
            subscriberReportEntity.setTariffIndex(subscriberReportDto.getTariffIndex());
        }
        if(subscriberReportDto.getNumberPhone() != null &&
                subscriberReportDto.getNumberPhone().length() >0 &&
                !subscriberReportDto.getNumberPhone().equals(subscriberReportEntity.getNumberPhone())
        ){
            subscriberReportEntity.setNumberPhone(subscriberReportDto.getNumberPhone());
        }
        if(!subscriberReportDto.getPayloads().equals(subscriberReportEntity.getPayloads())){
            subscriberReportEntity.setPayloads(subscriberReportDto.getPayloads());
        }
        if(subscriberReportDto.getTotalCost() > 0.0f &&
                !FloatCompare.isEquals(subscriberReportDto.getTotalCost(), subscriberReportEntity.getTotalCost())){
            subscriberReportEntity.setTotalCost(subscriberReportDto.getTotalCost());
        }
        if(subscriberReportDto.getMonetaryUnit() != null &&
                subscriberReportDto.getMonetaryUnit().length() >0 &&
                !subscriberReportDto.getMonetaryUnit().equals(subscriberReportEntity.getMonetaryUnit())
        ){
            subscriberReportEntity.setMonetaryUnit(subscriberReportDto.getMonetaryUnit());
        }

        service.updateSubscriberReport(subscriberReportEntity);
    }

    @DeleteMapping(path = "/{subscriberReportId}")
    public void deleteSubscriberReport(@PathVariable("subscriberReportId") Long subscriberReportId){
        service.deleteSubscriberReport(subscriberReportId);
    }

}
