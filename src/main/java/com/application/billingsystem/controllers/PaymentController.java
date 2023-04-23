package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.PaymentCreateDto;
import com.application.billingsystem.dto.PaymentDto;
import com.application.billingsystem.mapping.PaymentMapper;
import com.application.billingsystem.services.PaymentService;
import com.application.billingsystem.services.SubscriberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*/payment")
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
    public List<PaymentDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/numberPhone={numberPhone}")
    public List<PaymentDto> getAllByNumberPhone(@Valid @PathVariable("numberPhone") String numberPhone) {
        return service.getAllByNumberPhone(numberPhone)
                .stream()
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping("/id={id}")
    public PaymentDto findById(@Valid @PathVariable("id") Long id) {
        return mapper
                .getEntityToDto(service.getById(id));
    }

    @PostMapping("/")
    public void post(@Valid @RequestBody PaymentCreateDto createDto) {
        var paymentEntity = mapper.getCreateDtoToEntity(createDto);

        updateSubscriberBalance(mapper.getEntityToDto(paymentEntity), createDto.getMoney());
        service.create(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping("/")
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
    public void delete(@Valid @PathVariable("id") Long id) {
        service.delete(id);
    }

    private void updateSubscriberBalance(PaymentDto paymentDto, long totalMoney) {
        var subscriberEntity = subscriberService.getByNumberPhone(paymentDto.getNumberPhone());
        subscriberEntity.setBalance(subscriberEntity.getBalance() + totalMoney);
        subscriberService.update(subscriberEntity);
    }
}
