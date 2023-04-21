package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class TariffDto {

    private final long id;
    private final String tariffIndex; // id тарифа
    private final String nameTariff; //Название тарифа
    private final int minuteLimit; // Лимит минут
    private final float outBetBeforeLimit; // Ставка на исходящие звонки до превышения лимита
    private final float outBetAfterLimit; // Ставка на исходящие звонки после превышения лимита
    private final float inBetBeforeLimit; // Ставка на входящие звонки до превышения лимита
    private final float inBetAfterLimit; // Ставка на входящие звонки после превышения лимита
    private final int subscriberPayment; // Абонентская плата
}
