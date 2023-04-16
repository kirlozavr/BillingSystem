package com.application.billingsystem.controllers;

import com.application.billingsystem.FloatCompare;
import com.application.billingsystem.dto.TariffCreateDto;
import com.application.billingsystem.dto.TariffDto;
import com.application.billingsystem.entity.TariffEntity;
import com.application.billingsystem.mappers.TariffMapper;
import com.application.billingsystem.services.TariffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/tariff")
public class TariffController {

    private final TariffService service;
    private final TariffMapper mapper = new TariffMapper();

    public TariffController(TariffService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<TariffDto> getAllTariff(){
        return StreamSupport
                .stream(service.gelAllTariff().spliterator(), false)
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping(path = "/id={tariffId}")
    public TariffDto findById(@PathVariable("tariffId") Long tariffId){
        return mapper
                .getEntityToDto(service.getTariff(tariffId));
    }

    @GetMapping(path = "/tariff_index={tariffIndex}")
    public TariffDto findByIndex(@PathVariable("tariffIndex") String tariffIndex){
        return mapper
                .getEntityToDto(service.getTariff(tariffIndex));
    }

    @PostMapping
    public void postTariff(@RequestBody TariffCreateDto createDto){
        service.setNewTariff(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping(path = "/{tariffId}")
    public void putTariff(
            @PathVariable("tariffId") Long tariffId,
            @RequestBody TariffCreateDto createDto
    ){
        TariffEntity tariffEntity = service.getTariff(tariffId);

        if(createDto.getTariffIndex() != null &&
        createDto.getTariffIndex().length() >0 &&
        !createDto.getTariffIndex().equals(tariffEntity.getTariffIndex()) &&
        service.getTariff(createDto.getTariffIndex()) == null){
            tariffEntity.setTariffIndex(createDto.getTariffIndex());
        }
        if(createDto.getNameTariff() != null &&
                createDto.getNameTariff().length() >0 &&
                !createDto.getNameTariff().equals(tariffEntity.getNameTariff())){
            tariffEntity.setNameTariff(createDto.getNameTariff());
        }
        if(createDto.getMinuteLimit() > 0.0f &&
                !FloatCompare.isEquals(createDto.getMinuteLimit(), tariffEntity.getMinuteLimit())){
            tariffEntity.setMinuteLimit(createDto.getMinuteLimit());
        }
        if(createDto.getOutBet() > 0.0f &&
                !FloatCompare.isEquals(createDto.getOutBet(), tariffEntity.getOutBet())){
            tariffEntity.setOutBet(createDto.getOutBet());
        }
        if(createDto.getOutBetAfterLimit() > 0.0f &&
                !FloatCompare.isEquals(createDto.getOutBetAfterLimit(), tariffEntity.getOutBetAfterLimit())){
            tariffEntity.setOutBetAfterLimit(createDto.getOutBetAfterLimit());
        }
        if(createDto.getInBet() > 0.0f &&
                !FloatCompare.isEquals(createDto.getInBet(), tariffEntity.getInBet())){
            tariffEntity.setInBet(createDto.getInBet());
        }
        if(createDto.getSubscriberPayment() > 0.0f &&
                !FloatCompare.isEquals(createDto.getSubscriberPayment(), tariffEntity.getSubscriberPayment())){
            tariffEntity.setSubscriberPayment(createDto.getSubscriberPayment());
        }
        if(createDto.getMonetaryUnit() != null &&
                createDto.getMonetaryUnit().length() >0 &&
                !createDto.getMonetaryUnit().equals(tariffEntity.getMonetaryUnit())){
            tariffEntity.setMonetaryUnit(createDto.getMonetaryUnit());
        }

        service.updateTariff(tariffEntity);
    }

    @DeleteMapping(path = "/{tariffId}")
    public void deleteTariff(@PathVariable("tariffId") Long tariffId){
        service.deleteTariff(tariffId);
    }
}
