package com.application.billingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TariffCreateDto {

    private String tariffIndex; // id тарифа
    private String nameTariff; //Название тарифа
    private float minuteLimit; // Лимит минут
    private float outBet; // Ставка на исходящие звонки
    private float outBetAfterLimit; // Ставка на исходящие звонки после превышения лимита
    private float inBet; // Ставка на входящие звонки
    private float subscriberPayment; // Абонентская плата
    private String monetaryUnit;
}
