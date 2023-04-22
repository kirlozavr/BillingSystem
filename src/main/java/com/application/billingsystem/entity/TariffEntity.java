package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tariff")
public class TariffEntity {

    /**
     * Сущность тарифа
     **/

    @Id
    @SequenceGenerator(
            name = "tariff_id_seq",
            sequenceName = "tariff_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "tariff_id_seq"
    )
    private long id;
    private String tariffIndex; // id тарифа
    private String nameTariff; //Название тарифа
    private int minuteLimit; // Лимит минут
    private float outBetBeforeLimit; // Ставка на исходящие звонки до превышения лимита
    private float outBetAfterLimit; // Ставка на исходящие звонки после превышения лимита
    private float inBetBeforeLimit; // Ставка на входящие звонки до превышения лимита
    private float inBetAfterLimit; // Ставка на входящие звонки после превышения лимита
    private int subscriberPayment; // Абонентская плата
    private String monetaryUnit;

    public TariffEntity() {}

    public TariffEntity(
            String tariffIndex,
            String nameTariff,
            int minuteLimit,
            float outBetBeforeLimit,
            float outBetAfterLimit,
            float inBetBeforeLimit,
            float inBetAfterLimit,
            int subscriberPayment,
            String monetaryUnit
    ) {
        this.tariffIndex = tariffIndex;
        this.nameTariff = nameTariff;
        this.minuteLimit = minuteLimit;
        this.outBetBeforeLimit = outBetBeforeLimit;
        this.outBetAfterLimit = outBetAfterLimit;
        this.inBetBeforeLimit = inBetBeforeLimit;
        this.inBetAfterLimit = inBetAfterLimit;
        this.subscriberPayment = subscriberPayment;
        this.monetaryUnit = monetaryUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TariffEntity that)) return false;
        return id == that.id && Float.compare(that.minuteLimit, minuteLimit) == 0 && Float.compare(that.outBetBeforeLimit, outBetBeforeLimit) == 0 && Float.compare(that.outBetAfterLimit, outBetAfterLimit) == 0 && Float.compare(that.inBetBeforeLimit, inBetBeforeLimit) == 0 && Float.compare(that.subscriberPayment, subscriberPayment) == 0 && tariffIndex.equals(that.tariffIndex) && nameTariff.equals(that.nameTariff) && monetaryUnit.equals(that.monetaryUnit);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
