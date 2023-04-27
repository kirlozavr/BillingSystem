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
    private String tariffIndex; // индекс тарифа
    private String nameTariff; //Название тарифа
    private String nameOperator; //Название оператора
    private String nameLocation; //Локация, для которой предназначен тариф
    private String monetaryUnit;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TariffInfoEntity tariffInfo;

    public TariffEntity() {}

    public TariffEntity(
            String tariffIndex,
            String nameTariff,
            String nameOperator,
            String nameLocation,
            String monetaryUnit,
            TariffInfoEntity tariffInfo
    ) {
        this.tariffIndex = tariffIndex;
        this.nameTariff = nameTariff;
        this.nameOperator = nameOperator;
        this.nameLocation = nameLocation;
        this.monetaryUnit = monetaryUnit;
        this.tariffInfo = tariffInfo;
    }
}
