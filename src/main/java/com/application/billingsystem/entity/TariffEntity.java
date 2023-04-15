package com.application.billingsystem.entity;

import lombok.Data;

@Data
public class TariffEntity {

    private final String tariffId; // id тарифа
    private final String nameTariff; //Название тарифа
    private final float minuteLimit; // Лимит минут
    private final float outBet; // Ставка на исходящие звонки
    private final float outBetAfterLimit; // Ставка на исходящие звонки после превышения лимита
    private final float inBet; // Ставка на входящие звонки
    private final float subscriberPayment; // Абонентская плата
    private final String monetaryUnit;
}
