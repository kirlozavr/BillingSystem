package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.TariffCreateDto;
import com.application.billingsystem.dto.TariffDto;
import com.application.billingsystem.mapping.TariffMapper;
import com.application.billingsystem.services.TariffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/tariff")
@Tag(
        name = "Тариф",
        description = "Контроллер отвечает за crud операции с тарифами"
)
public class TariffController {
    private final TariffService service;
    private final TariffMapper mapper = new TariffMapper();

    public TariffController(TariffService service) {
        this.service = service;
    }

    @GetMapping("/all/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить все тарифы")
    public List<TariffDto> getAll() {
        return service.gelAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить тариф по id")
    public TariffDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @GetMapping("/index={index}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить тариф по индексу")
    public TariffDto findByIndex(@Valid @PathVariable("index") String tariffIndex) {
        return mapper
                .getEntityToDto(service.getByIndex(tariffIndex));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новый тариф")
    public void post(@Valid @RequestBody TariffCreateDto createDto) {
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить тариф")
    public void put(@Valid @RequestBody TariffDto tariffDto) {
        service.update(mapper.getDtoToEntity(tariffDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить тариф по id")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }
}
