package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.SubscriberEntity;
import com.application.billingsystem.entity.SubscriberReportEntity;
import com.application.billingsystem.main.FloatCompare;
import com.application.billingsystem.mapping.SubscriberReportMapper;
import com.application.billingsystem.services.SubscriberReportService;
import com.application.billingsystem.services.SubscriberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping("*/report")
public class SubscriberReportController {

    private final SubscriberReportService service;
    private final SubscriberService subscriberService;
    private final SubscriberReportMapper mapper = new SubscriberReportMapper();

    @Autowired
    public SubscriberReportController(
            SubscriberReportService service,
            SubscriberService subscriberService
    ) {
        this.service = service;
        this.subscriberService = subscriberService;
    }

    @GetMapping(path = "/")
    public List<SubscriberReportDto> getAllReports() {
        return StreamSupport
                .stream(service.getAllSubscriberReports().spliterator(), false)
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping(path = "/id={subscriberReportId}")
    public SubscriberReportDto findById(@PathVariable("subscriberReportId") Long subscriberReportId) {
        return mapper
                .getEntityToDto(service.getSubscriberReport(subscriberReportId));
    }

    @GetMapping(path = "/number_phone={numberPhone}")
    public SubscriberReportDto findByNumberPhone(@PathVariable("numberPhone") String numberPhone) {
        return mapper
                .getEntityToDto(service.getSubscriberReport(numberPhone));
    }

    @PostMapping
    public void postSubscriberReport(@RequestBody SubscriberReportDto subscriberReportDto) {
        service.createSubscriberReport(mapper.getDtoToEntity(subscriberReportDto));
        updateSubscriberBalance(subscriberReportDto, subscriberReportDto.getTotalCost());
    }

    @PutMapping(path = "/{subscriberReportId}")
    public void putSubscriberReport(
            @PathVariable("subscriberReportId") Long subscriberReportId,
            @RequestBody SubscriberReportDto subscriberReportDto
    ) {
        SubscriberReportEntity subscriberReportEntity = service.getSubscriberReport(subscriberReportId);
        float totalCost = subscriberReportDto.getTotalCost();


        if (subscriberReportDto.getTariffIndex() != null &&
                subscriberReportDto.getTariffIndex().length() > 0 &&
                !subscriberReportDto.getTariffIndex().equals(subscriberReportEntity.getTariffIndex())
        ) {
            subscriberReportEntity.setTariffIndex(subscriberReportDto.getTariffIndex());
        }
        if (subscriberReportDto.getNumberPhone() != null &&
                subscriberReportDto.getNumberPhone().length() > 0 &&
                !subscriberReportDto.getNumberPhone().equals(subscriberReportEntity.getNumberPhone())
        ) {
            subscriberReportEntity.setNumberPhone(subscriberReportDto.getNumberPhone());
        }
        if (!subscriberReportDto.getPayloads().equals(subscriberReportEntity.getPayloads())) {
            subscriberReportEntity.setPayloads(subscriberReportDto.getPayloads());
        }
        if (subscriberReportDto.getTotalCost() > 0.0f &&
                !FloatCompare.isEquals(subscriberReportDto.getTotalCost(), subscriberReportEntity.getTotalCost())
        ) {
            totalCost -= subscriberReportEntity.getTotalCost();
            subscriberReportEntity.setTotalCost(subscriberReportDto.getTotalCost());
        }
        if (subscriberReportDto.getMonetaryUnit() != null &&
                subscriberReportDto.getMonetaryUnit().length() > 0 &&
                !subscriberReportDto.getMonetaryUnit().equals(subscriberReportEntity.getMonetaryUnit())
        ) {
            subscriberReportEntity.setMonetaryUnit(subscriberReportDto.getMonetaryUnit());
        }

        updateSubscriberBalance(subscriberReportDto, totalCost);
        service.updateSubscriberReport(subscriberReportEntity);
    }

    @DeleteMapping(path = "/{subscriberReportId}")
    public void deleteSubscriberReport(@PathVariable("subscriberReportId") Long subscriberReportId) {
        service.deleteSubscriberReport(subscriberReportId);
    }

    private void updateSubscriberBalance(SubscriberReportDto subscriberReportDto, float totalCost) {
        SubscriberEntity subscriberEntity = subscriberService
                .getSubscriber(subscriberReportDto.getNumberPhone());
        subscriberEntity.setBalance((long) (subscriberEntity.getBalance() - Math.ceil(totalCost)));
        subscriberService.updateSubscriber(subscriberEntity);
    }

}
