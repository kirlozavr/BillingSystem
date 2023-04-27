package com.application.billingsystem.dto;

import com.application.billingsystem.entity.TariffInfoLocationEntity;
import com.application.billingsystem.entity.TariffInfoOperatorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TariffInfoDto {

    private long id;
    private int minuteLimit; // Лимит минут
    private float outBetBeforeLimit; // Ставка на исходящие звонки до превышения лимита
    private float outBetAfterLimit; // Ставка на исходящие звонки после превышения лимита
    private float inBetBeforeLimit; // Ставка на входящие звонки до превышения лимита
    private float inBetAfterLimit; // Ставка на входящие звонки после превышения лимита
    private int subscriberPayment; // Абонентская плата
    private TariffInfoOperatorEntity tariffInfoOperator;
    private TariffInfoLocationEntity tariffInfoLocation;
}
