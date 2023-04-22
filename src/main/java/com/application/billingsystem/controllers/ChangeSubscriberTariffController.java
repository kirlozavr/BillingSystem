package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.ChangeSubscriberTariffCreateDto;
import com.application.billingsystem.dto.ChangeSubscriberTariffDto;
import com.application.billingsystem.entity.ChangeSubscriberTariffEntity;
import com.application.billingsystem.entity.SubscriberEntity;
import com.application.billingsystem.mapping.ChangeSubscriberTariffMapper;
import com.application.billingsystem.services.ChangeSubscriberTariffService;
import com.application.billingsystem.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("*/changeTariff")
public class ChangeSubscriberTariffController {

    private final ChangeSubscriberTariffService service;
    private final SubscriberService subscriberService;
    private final ChangeSubscriberTariffMapper mapper = new ChangeSubscriberTariffMapper();

    @Autowired
    public ChangeSubscriberTariffController(
            ChangeSubscriberTariffService service,
            SubscriberService subscriberService
    ) {
        this.service = service;
        this.subscriberService = subscriberService;
    }

    @GetMapping("/")
    public List<ChangeSubscriberTariffDto> getAllChangeSubscriberTariffs() {
        return StreamSupport
                .stream(service.getAllChangeSubscriberTariffs().spliterator(), false)
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping(path = "/id={changeSubscriberTariffId}")
    public ChangeSubscriberTariffDto findById(@PathVariable("changeSubscriberTariffId") Long ChangeSubscriberTariffId) {
        return mapper
                .getEntityToDto(service.getChangeSubscriberTariff(ChangeSubscriberTariffId));
    }

    @PostMapping
    public void postChangeSubscriberTariff(@RequestBody ChangeSubscriberTariffCreateDto createDto) {
        service.createChangeSubscriberTariff(mapper.getCreateDtoToEntity(createDto));
        updateSubscriberTariff(createDto);
    }

    @PutMapping(path = "/{changeSubscriberTariffId}")
    public void putChangeSubscriberTariff(
            @PathVariable("changeSubscriberTariffId") Long changeSubscriberTariffId,
            @RequestBody ChangeSubscriberTariffCreateDto createDto
    ) {
        ChangeSubscriberTariffEntity changeSubscriberTariffEntity =
                service.getChangeSubscriberTariff(changeSubscriberTariffId);

        if (createDto.getNumberPhone() != null &&
                createDto.getNumberPhone().length() > 0 &&
                !createDto.getNumberPhone().equals(changeSubscriberTariffEntity.getNumberPhone())) {
            changeSubscriberTariffEntity.setNumberPhone(createDto.getNumberPhone());
        }
        if (createDto.getTariffIndex() != null &&
                createDto.getTariffIndex().length() > 0 &&
                !createDto.getTariffIndex().equals(changeSubscriberTariffEntity.getTariffIndex())) {
            changeSubscriberTariffEntity.setTariffIndex(createDto.getTariffIndex());
        }

        updateSubscriberTariff(createDto);
        service.updateChangeSubscriberTariff(changeSubscriberTariffEntity);
    }

    @DeleteMapping("/{changeSubscriberTariffId}")
    public void deleteChangeSubscriberTariff(@PathVariable("changeSubscriberTariffId") Long changeSubscriberTariffId) {
        service.deleteChangeSubscriberTariff(changeSubscriberTariffId);
    }

    private void updateSubscriberTariff(ChangeSubscriberTariffCreateDto createDto) {
        SubscriberEntity subscriberEntity = subscriberService.getSubscriber(createDto.getNumberPhone());
        subscriberEntity.setTariffIndex(createDto.getTariffIndex());
        subscriberService.updateSubscriber(subscriberEntity);
    }

}
