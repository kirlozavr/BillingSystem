package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.SubscriberCreateDto;
import com.application.billingsystem.dto.SubscriberDto;
import com.application.billingsystem.mapping.SubscriberMapper;
import com.application.billingsystem.services.SubscriberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/subscriber")
public class SubscriberController {

    private final SubscriberService service;
    private final SubscriberMapper mapper = new SubscriberMapper();

    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<SubscriberDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    public SubscriberDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @GetMapping("/numberPhone={numberPhone}")
    public SubscriberDto findByNumberPhone(@Valid @PathVariable("numberPhone") String numberPhone) {
        return mapper
                .getEntityToDto(service.getByNumberPhone(numberPhone));
    }

    @GetMapping("/numberPhoneAndPositiveBalance={numberPhone}")
    public SubscriberDto findByNumberPhoneAndPositiveBalance(@Valid @PathVariable("numberPhone") String numberPhone) {
        return mapper
                .getEntityToDto(service.getByNumberPhoneAndPositiveBalance(numberPhone));
    }

    @PostMapping("/")
    public void post(@Valid @RequestBody SubscriberCreateDto createDto) {
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping(path = "/")
    public void put(@Valid @RequestBody SubscriberDto subscriberDto) {
        service.update(mapper.getDtoToEntity(subscriberDto));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }
}
