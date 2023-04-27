package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tariff_info")
public class TariffInfoEntity {

    @Id
    @SequenceGenerator(
            name = "tariff_info_id_seq",
            sequenceName = "tariff_info_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "tariff_info_id_seq"
    )
    private long id;
    private int minuteLimit; // Лимит минут
    private float outBetBeforeLimit; // Ставка на исходящие звонки до превышения лимита
    private float outBetAfterLimit; // Ставка на исходящие звонки после превышения лимита
    private float inBetBeforeLimit; // Ставка на входящие звонки до превышения лимита
    private float inBetAfterLimit; // Ставка на входящие звонки после превышения лимита
    private int subscriberPayment; // Абонентская плата
    /** Информация о стоимости услуг при звонках другому оператору **/
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TariffInfoOperatorEntity tariffInfoOperator;

    /** Информация о стоимости услуг при звонках на другую локацию **/
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TariffInfoLocationEntity tariffInfoLocation;

    public TariffInfoEntity(){}

    public TariffInfoEntity(
            int minuteLimit,
            float outBetBeforeLimit,
            float outBetAfterLimit,
            float inBetBeforeLimit,
            float inBetAfterLimit,
            int subscriberPayment,
            TariffInfoOperatorEntity tariffInfoOperator,
            TariffInfoLocationEntity tariffInfoLocation
    ) {
        this.minuteLimit = minuteLimit;
        this.outBetBeforeLimit = outBetBeforeLimit;
        this.outBetAfterLimit = outBetAfterLimit;
        this.inBetBeforeLimit = inBetBeforeLimit;
        this.inBetAfterLimit = inBetAfterLimit;
        this.subscriberPayment = subscriberPayment;
        this.tariffInfoOperator = tariffInfoOperator;
        this.tariffInfoLocation = tariffInfoLocation;
    }
}
