package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.PayloadCreateDto;
import com.application.billingsystem.dto.PayloadDto;
import com.application.billingsystem.mapping.PayloadMapper;
import com.application.billingsystem.services.PayloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/payload")
@Tag(
        name = "Звонок",
        description = "Контроллер позволяет выполнять основные crud операции с информацией о звонках"
)
public class PayloadController {

    private final PayloadService service;
    private final PayloadMapper mapper = new PayloadMapper();

    @Autowired
    public PayloadController(PayloadService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить все звонки")
    public List<PayloadDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить звонок по id")
    public PayloadDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новый звонок")
    public void post(@Valid @RequestBody PayloadCreateDto createDto) {
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменить информацию о звонке")
    public void put(@Valid @RequestBody PayloadDto createDto) {
        service.update(mapper.getDtoToEntity(createDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить звонок по id")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }
}
