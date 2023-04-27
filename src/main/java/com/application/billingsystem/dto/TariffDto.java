package com.application.billingsystem.dto;

import com.application.billingsystem.entity.TariffInfoEntity;
import lombok.Data;

@Data
public class TariffDto {

    private final long id;
    private final String tariffIndex; // id тарифа
    private final String nameTariff; // Название тарифа
    private final String nameOperator; // Название оператора
    private final String nameLocation; // Локация
    private final String monetaryUnit;
    private final TariffInfoEntity tariffInformation;
}
