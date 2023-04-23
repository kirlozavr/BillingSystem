package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.SubscriberCreateDto;
import com.application.billingsystem.dto.SubscriberDto;
import com.application.billingsystem.mapping.SubscriberMapper;
import com.application.billingsystem.services.SubscriberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/subscriber")
@Tag(name = "Абонент", description = "Контроллер позволяет выполнять основные crud операции с абонентами")
public class SubscriberController {

    private final SubscriberService service;
    private final SubscriberMapper mapper = new SubscriberMapper();

    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить всех абонентов")
    public List<SubscriberDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить абонента по id")
    public SubscriberDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @GetMapping("/numberPhone={numberPhone}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить абонента по номеру телефона")
    public SubscriberDto findByNumberPhone(@Valid @PathVariable("numberPhone") String numberPhone) {
        return mapper
                .getEntityToDto(service.getByNumberPhone(numberPhone));
    }

    @GetMapping("/numberPhoneAndPositiveBalance={numberPhone}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить абонента по номеру телефона, у которого баланс больше 0")
    public SubscriberDto findByNumberPhoneAndPositiveBalance(@Valid @PathVariable("numberPhone") String numberPhone) {
        return mapper
                .getEntityToDto(service.getByNumberPhoneAndPositiveBalance(numberPhone));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать нового абонента")
    public void post(@Valid @RequestBody SubscriberCreateDto createDto) {
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменить абонента")
    public void put(@Valid @RequestBody SubscriberDto subscriberDto) {
        service.update(mapper.getDtoToEntity(subscriberDto));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить абонента по id")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }
}
