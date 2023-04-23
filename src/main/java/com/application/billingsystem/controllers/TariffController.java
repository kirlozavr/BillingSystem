package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.TariffCreateDto;
import com.application.billingsystem.dto.TariffDto;
import com.application.billingsystem.mapping.TariffMapper;
import com.application.billingsystem.services.TariffService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/tariff")
public class TariffController {
    private final TariffService service;
    private final TariffMapper mapper = new TariffMapper();

    public TariffController(TariffService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<TariffDto> getAll() {
        return service.gelAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    public TariffDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @GetMapping("/index={index}")
    public TariffDto findByIndex(@Valid @PathVariable("index") String tariffIndex) {
        return mapper
                .getEntityToDto(service.getByIndex(tariffIndex));
    }

    @PostMapping("/")
    public void post(@Valid @RequestBody TariffCreateDto createDto) {
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
    public void put(@Valid @RequestBody TariffDto tariffDto) {
        service.update(mapper.getDtoToEntity(tariffDto));
    }

    @DeleteMapping("/{id}")
    public void deleteTariff(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }
}
