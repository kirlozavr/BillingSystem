package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.PaymentCreateDto;
import com.application.billingsystem.dto.PaymentDto;
import com.application.billingsystem.mapping.PaymentMapper;
import com.application.billingsystem.services.PaymentService;
import com.application.billingsystem.services.SubscriberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/payment")
@Tag(name = "Платеж", description = "Контроллер позволяет выполнять основные crud операции с платежами")
public class PaymentController {

    private final PaymentService service;
    private final SubscriberService subscriberService;
    private final PaymentMapper mapper = new PaymentMapper();

    @Autowired
    public PaymentController(
            PaymentService service,
            SubscriberService subscriberService
    ) {
        this.service = service;
        this.subscriberService = subscriberService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить все платежи")
    public List<PaymentDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/all/numberPhone={numberPhone}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить все платежи абонента по номеру телефона")
    public List<PaymentDto> getAllByNumberPhone(@Valid @PathVariable("numberPhone") String numberPhone) {
        return service.getAllByNumberPhone(numberPhone)
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить платеж по id")
    public PaymentDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новый платеж, при создании платежа - у абонента изменяется баланс")
    public void post(@Valid @RequestBody PaymentCreateDto createDto) {
        var paymentEntity = mapper.getCreateDtoToEntity(createDto);

        updateSubscriberBalance(mapper.getEntityToDto(paymentEntity), createDto.getMoney());
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменить платеж, при изменении платежа - у абонента изменяется баланс с учетом предыдущего платежа")
    public void put(@Valid @RequestBody PaymentDto paymentDto) {
        var paymentEntity = service.getById(paymentDto.getId());
        long totalMoney = 0;

        if (paymentDto.getMoney() != paymentEntity.getMoney()) {
            totalMoney = paymentDto.getMoney() - paymentEntity.getMoney();
        }

        updateSubscriberBalance(paymentDto, totalMoney);
        service.update(paymentEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить платеж по id")
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }

    private void updateSubscriberBalance(PaymentDto paymentDto, long totalMoney) {
        var subscriberEntity = subscriberService.getByNumberPhone(paymentDto.getNumberPhone());
        subscriberEntity.setBalance(subscriberEntity.getBalance() + totalMoney);
        subscriberService.update(subscriberEntity);
    }
}
