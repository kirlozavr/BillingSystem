package com.application.billingsystem.dto;

import com.application.billingsystem.entity.TariffInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TariffCreateDto {

    private String tariffIndex; // id тарифа
    private String nameTariff; //Название тарифа
    private int minuteLimit; // Лимит минут
    private String nameOperator; //Название оператора
    private String targetLocation; //Локация, для которой предназначен тариф
    private String monetaryUnit;
    private TariffInfoEntity tariffInformation;
}
