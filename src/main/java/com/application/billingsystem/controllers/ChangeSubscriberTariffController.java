package com.application.billingsystem.controllers;

import com.application.billingsystem.dto.ChangeSubscriberTariffCreateDto;
import com.application.billingsystem.dto.ChangeSubscriberTariffDto;
import com.application.billingsystem.entity.ChangeSubscriberTariffEntity;
import com.application.billingsystem.mapping.ChangeSubscriberTariffMapper;
import com.application.billingsystem.services.ChangeSubscriberTariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/changeSubscriberTariffController")
public class ChangeSubscriberTariffController {

    private final ChangeSubscriberTariffService service;
    private final ChangeSubscriberTariffMapper mapper = new ChangeSubscriberTariffMapper();

    @Autowired
    public ChangeSubscriberTariffController(ChangeSubscriberTariffService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<ChangeSubscriberTariffDto> getAllChangeSubscriberTariffs(){
        return StreamSupport
                .stream(service.getAllChangeSubscriberTariffs().spliterator(), false)
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping(path = "/id={changeSubscriberTariffId}")
    public ChangeSubscriberTariffDto findById(@PathVariable("changeSubscriberTariffId") Long ChangeSubscriberTariffId){
        return mapper
                .getEntityToDto(service.getChangeSubscriberTariff(ChangeSubscriberTariffId));
    }

    @PostMapping
    public void postChangeSubscriberTariff(@RequestBody ChangeSubscriberTariffCreateDto createDto){
        service.createChangeSubscriberTariff(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping(path = "/{changeSubscriberTariffId}")
    public void putPayload(
            @PathVariable("changeSubscriberTariffId") Long changeSubscriberTariffId,
            @RequestBody ChangeSubscriberTariffCreateDto createDto
    ) {
        ChangeSubscriberTariffEntity changeSubscriberTariffEntity = service.getChangeSubscriberTariff(changeSubscriberTariffId);

        if (createDto.getNumberPhone() != null &&
                createDto.getNumberPhone().length() > 0 &&
                !createDto.getNumberPhone().equals(changeSubscriberTariffEntity.getNumberPhone())) {
            changeSubscriberTariffEntity.setNumberPhone(createDto.getNumberPhone());
        }
        if (createDto.getTariffIndex() != null &&
                createDto.getTariffIndex().length() > 0 &&
                !createDto.getTariffIndex().equals(changeSubscriberTariffEntity.getTariffIndex())) {
            changeSubscriberTariffEntity.setTariffIndex(createDto.getTariffIndex());
        }

        service.updateChangeSubscriberTariff(changeSubscriberTariffEntity);
    }

    @DeleteMapping("/{changeSubscriberTariffId}")
    public void deleteChangeSubscriberTariff(@PathVariable("changeSubscriberTariffId") Long changeSubscriberTariffId){
        service.deleteChangeSubscriberTariff(changeSubscriberTariffId);
    }
}
