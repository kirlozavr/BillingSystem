package com.application.billingsystem.controllers;

import com.application.billingsystem.FloatCompare;
import com.application.billingsystem.dto.PayCreateDto;
import com.application.billingsystem.dto.PayDto;
import com.application.billingsystem.entity.PayEntity;
import com.application.billingsystem.mapping.PayMapper;
import com.application.billingsystem.services.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/pay")
public class PayController {

    private final PayService service;
    private final PayMapper mapper = new PayMapper();

    @Autowired
    public PayController(PayService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<PayDto> getAllPay() {
        return StreamSupport
                .stream(service.getAllPay().spliterator(), false)
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping(path = "/id={payId}")
    public PayDto findById(@PathVariable("payId") Long payId) {
        return mapper
                .getEntityToDto(service.getPay(payId));
    }

    @PostMapping
    public void postPay(@RequestBody PayCreateDto createDto) {
        service.createPay(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping(path = "/{payId}")
    public void putPay(
            @PathVariable("payId") Long payId,
            @RequestBody PayCreateDto createDto
    ) {
        PayEntity payEntity = service.getPay(payId);

        if (createDto.getNumberPhone() != null &&
                createDto.getNumberPhone().length() > 0 &&
                !createDto.getNumberPhone().equals(payEntity.getNumberPhone())) {
            payEntity.setNumberPhone(createDto.getNumberPhone());
        }
        if (createDto.getMoney() > 0.0f &&
                !FloatCompare.isEquals(createDto.getMoney(), payEntity.getMoney())) {
            payEntity.setMoney(createDto.getMoney());
        }

        service.updatePay(payEntity);
    }

    @DeleteMapping("/{payId}")
    public void deletePay(@PathVariable("payId") Long payId) {
        service.deletePay(payId);
    }
}
