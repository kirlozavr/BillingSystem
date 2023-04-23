package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.utils.FloatCompare;
import com.application.billingsystem.mapping.SubscriberReportMapper;
import com.application.billingsystem.services.SubscriberReportService;
import com.application.billingsystem.services.SubscriberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public List<SubscriberReportDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/numberPhone={numberPhone}")
    public List<SubscriberReportDto> getAllByNumberPhone(@Valid @PathVariable("numberPhone") String numberPhone) {
        return service.getAllByNumberPhone(numberPhone)
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    public SubscriberReportDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @PostMapping("/")
    public void post(@Valid @RequestBody SubscriberReportDto subscriberReportDto) {
        updateSubscriberBalance(subscriberReportDto, subscriberReportDto.getTotalCost());
        service.create(mapper.getDtoToEntity(subscriberReportDto));
    }

    @PutMapping("/{id}")
    public void put(@Valid @RequestBody SubscriberReportDto subscriberReportDto) {
        var subscriberReportEntity = service.getById(subscriberReportDto.getId());
        float totalCost = 0;

        if (
                !FloatCompare.isEquals(
                        subscriberReportDto.getTotalCost(),
                        subscriberReportEntity.getTotalCost()
                )
        ) {
            totalCost = subscriberReportEntity.getTotalCost() - subscriberReportDto.getTotalCost();
        }

        updateSubscriberBalance(subscriberReportDto, totalCost);
        service.update(subscriberReportEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }

    private void updateSubscriberBalance(SubscriberReportDto subscriberReportDto, float totalCost) {
        var subscriberEntity = subscriberService
                .getByNumberPhone(subscriberReportDto.getNumberPhone());
        subscriberEntity.setBalance((long) (subscriberEntity.getBalance() + Math.ceil(totalCost)));
        subscriberService.update(subscriberEntity);
    }

}
