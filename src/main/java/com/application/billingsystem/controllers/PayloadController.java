package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.PayloadCreateDto;
import com.application.billingsystem.dto.PayloadDto;
import com.application.billingsystem.mapping.PayloadMapper;
import com.application.billingsystem.services.PayloadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/payload")
public class PayloadController {

    private final PayloadService service;
    private final PayloadMapper mapper = new PayloadMapper();

    @Autowired
    public PayloadController(PayloadService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<PayloadDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    public PayloadDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @PostMapping("/")
    public void post(@Valid @RequestBody PayloadCreateDto createDto) {
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
    public void put(@Valid @RequestBody PayloadDto createDto) {
        service.update(mapper.getDtoToEntity(createDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }
}
