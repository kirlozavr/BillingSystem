package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.TariffInfoLocationCreateDto;
import com.application.billingsystem.dto.TariffInfoLocationDto;
import com.application.billingsystem.mapping.TariffInfoLocationMapper;
import com.application.billingsystem.services.TariffInfoLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/tariffInfoLocation")
@Tag(
        name = "Информация о стоимости услуг при звонках на другую локацию",
        description = "Контроллер позволяет выполнять основные crud операции " +
                "с информацией о стоимости услуг при звонках на другую локацию"
)
public class TariffInfoLocationController {

    private final TariffInfoLocationService service;
    private final TariffInfoLocationMapper mapper = new TariffInfoLocationMapper();

    public TariffInfoLocationController(TariffInfoLocationService service) {
        this.service = service;
    }

    @GetMapping("/all/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить всю информацию о стоимости услуг при звонках на другую локацию")
    public List<TariffInfoLocationDto> getAll() {
        return service.gelAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить информацию о стоимости услуг при звонках на другую локацию по id")
    public TariffInfoLocationDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новую информацию о стоимости услуг при звонках на другую локацию")
    public void post(@Valid @RequestBody TariffInfoLocationCreateDto createDto) {
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить информацию о стоимости услуг при звонках на другую локацию")
    public void put(@Valid @RequestBody TariffInfoLocationDto tariffInfoLocationDto) {
        service.update(mapper.getDtoToEntity(tariffInfoLocationDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить информацию о стоимости услуг при звонках на другую локацию по id")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }
}
