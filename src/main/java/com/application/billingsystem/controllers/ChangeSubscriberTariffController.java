package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.ChangeSubscriberTariffCreateDto;
import com.application.billingsystem.dto.ChangeSubscriberTariffDto;
import com.application.billingsystem.mapping.ChangeSubscriberTariffMapper;
import com.application.billingsystem.services.ChangeSubscriberTariffService;
import com.application.billingsystem.services.SubscriberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/changeTariff")
@Tag(name = "Изменение тарифа у абонента", description = "Контроллер позволяет выполнять основные crud операции с изменением тарифа у абонента")
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
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить всю информацию об изменении тарифа")
    public List<ChangeSubscriberTariffDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/numberPhone={numberPhone}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить всю информацию об изменении тарифов у абонента по номеру телефона")
    public List<ChangeSubscriberTariffDto> getAllByNumberPhone(@Valid @PathVariable("numberPhone") String numberPhone) {
        return service.getAllByNumberPhone(numberPhone)
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить информацию об изменении тарифа по id")
    public ChangeSubscriberTariffDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новое изменение тарифа, при создании - изменится тариф у абонента")
    public void post(@Valid @RequestBody ChangeSubscriberTariffCreateDto createDto) {
        var changeSubscriberTariffEntity = mapper.getCreateDtoToEntity(createDto);

        updateSubscriberTariff(mapper.getEntityToDto(changeSubscriberTariffEntity));
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменить информацию о смене тарифа, при изменении - изменится тариф у абонента")
    public void put(@Valid @RequestBody ChangeSubscriberTariffDto changeSubscriberTariffDto) {
        updateSubscriberTariff(changeSubscriberTariffDto);
        service.update(mapper.getDtoToEntity(changeSubscriberTariffDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить информацию об изменении тарифа по id")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }

    private void updateSubscriberTariff(ChangeSubscriberTariffDto createDto) {
        var subscriberEntity = subscriberService.getByNumberPhone(createDto.getNumberPhone());
        subscriberEntity.setTariffIndex(createDto.getTariffIndex());
        subscriberService.update(subscriberEntity);
    }

}
