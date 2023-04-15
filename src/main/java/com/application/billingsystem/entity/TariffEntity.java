package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tariff")
public class TariffEntity {

    @Id
    @SequenceGenerator(
            name = "tariff_id_seq",
            sequenceName = "tariff_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tariff_id_seq"
    )
    private long id;
    private String tariffIndex; // id тарифа
    private String nameTariff; //Название тарифа
    private float minuteLimit; // Лимит минут
    private float outBet; // Ставка на исходящие звонки
    private float outBetAfterLimit; // Ставка на исходящие звонки после превышения лимита
    private float inBet; // Ставка на входящие звонки
    private float subscriberPayment; // Абонентская плата
    private String monetaryUnit;

    public TariffEntity() {
    }

    public TariffEntity(
            String tariffIndex,
            String nameTariff,
            float minuteLimit,
            float outBet,
            float outBetAfterLimit,
            float inBet,
            float subscriberPayment,
            String monetaryUnit
    ) {
        this.tariffIndex = tariffIndex;
        this.nameTariff = nameTariff;
        this.minuteLimit = minuteLimit;
        this.outBet = outBet;
        this.outBetAfterLimit = outBetAfterLimit;
        this.inBet = inBet;
        this.subscriberPayment = subscriberPayment;
        this.monetaryUnit = monetaryUnit;
    }
}
