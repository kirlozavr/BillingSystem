package com.application.billingsystem.controllers;

import com.application.billingsystem.entity.SubscriberEntity;
import com.application.billingsystem.main.FloatCompare;
import com.application.billingsystem.dto.PaymentCreateDto;
import com.application.billingsystem.dto.PaymentDto;
import com.application.billingsystem.entity.PaymentEntity;
import com.application.billingsystem.mapping.PaymentMapper;
import com.application.billingsystem.services.PaymentService;
import com.application.billingsystem.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

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
    public List<PaymentDto> getAllPayments() {
        return StreamSupport
                .stream(service.getAllPayments().spliterator(), false)
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping(path = "/id={paymentId}")
    public PaymentDto findById(@PathVariable("paymentId") Long paymentId) {
        return mapper
                .getEntityToDto(service.getPayment(paymentId));
    }

    @PostMapping
    public void postPayment(@RequestBody PaymentCreateDto createDto) {
        service.createPayment(mapper.getCreateDtoToEntity(createDto));
        updateSubscriberBalance(createDto, createDto.getMoney());
    }

    @PutMapping(path = "/{paymentId}")
    public void putPayment(
            @PathVariable("paymentId") Long paymentId,
            @RequestBody PaymentCreateDto createDto
    ) {
        PaymentEntity paymentEntity = service.getPayment(paymentId);
        float totalMoney = createDto.getMoney();

        if (createDto.getNumberPhone() != null &&
                createDto.getNumberPhone().length() > 0 &&
                !createDto.getNumberPhone().equals(paymentEntity.getNumberPhone())) {
            paymentEntity.setNumberPhone(createDto.getNumberPhone());
        }
        if (createDto.getMoney() > 0.0f &&
                !FloatCompare.isEquals(createDto.getMoney(), paymentEntity.getMoney())) {
            totalMoney -= paymentEntity.getMoney();
            paymentEntity.setMoney(createDto.getMoney());
        }

        updateSubscriberBalance(createDto, totalMoney);
        service.updatePayment(paymentEntity);
    }

    @DeleteMapping("/{paymentId}")
    public void deletePayment(@PathVariable("paymentId") Long payId) {
        service.deletePayment(payId);
    }

    private void updateSubscriberBalance(PaymentCreateDto createDto, float totalMoney) {
        SubscriberEntity subscriberEntity = subscriberService.getSubscriber(createDto.getNumberPhone());
        subscriberEntity.setBalance(subscriberEntity.getBalance() + totalMoney);
        subscriberService.updateSubscriber(subscriberEntity);
    }
}
