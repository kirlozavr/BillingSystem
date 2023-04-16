package com.application.billingsystem.controllers;

import com.application.billingsystem.FloatCompare;
import com.application.billingsystem.dto.PayloadCreateDto;
import com.application.billingsystem.dto.PayloadDto;
import com.application.billingsystem.entity.PayloadEntity;
import com.application.billingsystem.mapping.PayloadMapper;
import com.application.billingsystem.services.PayloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/payload")
public class PayloadController {

    private final PayloadService service;
    private final PayloadMapper mapper = new PayloadMapper();

    @Autowired
    public PayloadController(PayloadService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<PayloadDto> getAllPayloads(){
        return StreamSupport
                .stream(service.getAllPayloads().spliterator(), false)
                .map(mapper::getEntityToDto)
                .toList();
    }

    @GetMapping(path = "/id={payloadId}")
    public PayloadDto findById(@PathVariable("payloadId") Long payloadId){
        return mapper
                .getEntityToDto(service.getPayload(payloadId));
    }

    @PostMapping
    public void postPayload(@RequestBody PayloadCreateDto createDto){
        service.createPayload(mapper.getCreateDtoToEntity(createDto));
    }

    @PutMapping(path = "/{payloadId}")
    public void putPayload(
            @PathVariable("payloadId") Long payloadId,
            @RequestBody PayloadCreateDto createDto
    ){
        PayloadEntity payloadEntity = service.getPayload(payloadId);

        if(createDto.getCallType() != null &&
                createDto.getCallType().length() >0 &&
                !createDto.getCallType().equals(payloadEntity.getCallType())){
            payloadEntity.setCallType(createDto.getCallType());
        }
        if(createDto.getStartTime() != null &&
                createDto.getStartTime().length() >0 &&
                !createDto.getStartTime().equals(payloadEntity.getStartTime())){
            payloadEntity.setStartTime(createDto.getStartTime());
        }
        if(createDto.getEndTime() != null &&
                createDto.getEndTime().length() >0 &&
                !createDto.getEndTime().equals(payloadEntity.getEndTime())){
            payloadEntity.setEndTime(createDto.getEndTime());
        }
        if(createDto.getDuration() != null &&
                createDto.getDuration().length() >0 &&
                !createDto.getDuration().equals(payloadEntity.getDuration())){
            payloadEntity.setDuration(createDto.getDuration());
        }
        if(createDto.getCost() > 0.0f &&
                !FloatCompare.isEquals(createDto.getCost(), payloadEntity.getCost())){
            payloadEntity.setCost(createDto.getCost());
        }

        service.updatePayload(payloadEntity);
    }

    @DeleteMapping("/{payloadId}")
    public void deletePayload(@PathVariable("payloadId") Long payloadId){
        service.deletePayload(payloadId);
    }
}
