package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.TariffInfoOperatorCreateDto;
import com.application.billingsystem.dto.TariffInfoOperatorDto;
import com.application.billingsystem.mapping.TariffInfoOperatorMapper;
import com.application.billingsystem.services.TariffInfoOperatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/tariffInfoOperator")
@Tag(
        name = "Информация о стоимости услуг при звонках другому оператору",
        description = "Контроллер позволяет выполнять основные crud операции " +
                "с информацией о стоимости услуг при звонках другому оператору"
)
public class TariffInfoOperatorController {

    private final TariffInfoOperatorService service;
    private final TariffInfoOperatorMapper mapper = new TariffInfoOperatorMapper();

    public TariffInfoOperatorController(TariffInfoOperatorService service) {
        this.service = service;
    }

    @GetMapping("/all/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить всю информацию о стоимости услуг при звонках другому оператору")
    public List<TariffInfoOperatorDto> getAll() {
        return service.gelAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить информацию о стоимости услуг при звонках другому оператору по id")
    public TariffInfoOperatorDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новую информацию о стоимости услуг при звонках другому оператору")
    public void post(@Valid @RequestBody TariffInfoOperatorCreateDto createDto) {
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить информацию о стоимости услуг при звонках другому оператору")
    public void put(@Valid @RequestBody TariffInfoOperatorDto tariffInfoOperatorDto) {
        service.update(mapper.getDtoToEntity(tariffInfoOperatorDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить информацию о стоимости услуг при звонках другому оператору по id")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }
}
