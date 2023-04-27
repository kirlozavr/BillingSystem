package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.TariffInfoCreateDto;
import com.application.billingsystem.dto.TariffInfoDto;
import com.application.billingsystem.mapping.TariffInfoMapper;
import com.application.billingsystem.services.TariffInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/tariffInfo")
@Tag(
        name = "Информация о тарифе",
        description = "Контроллер позволяет выполнять основные crud операции с информацией о тарифе"
)
public class TariffInfoController {
    private final TariffInfoService service;
    private final TariffInfoMapper mapper = new TariffInfoMapper();

    public TariffInfoController(TariffInfoService service) {
        this.service = service;
    }

    @GetMapping("/all/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить всю информацию о тарифах")
    public List<TariffInfoDto> getAll() {
        return service.gelAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить информацию о тарифе по id")
    public TariffInfoDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новую информацию о тарифе")
    public void post(@Valid @RequestBody TariffInfoCreateDto createDto) {
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить информацию о тарифе")
    public void put(@Valid @RequestBody TariffInfoDto tariffInfoDto) {
        service.update(mapper.getDtoToEntity(tariffInfoDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить информацию о тарифе по id")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }
}
