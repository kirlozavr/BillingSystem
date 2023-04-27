package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tariff_info_location")
public class TariffInfoLocationEntity {

    @Id
    @SequenceGenerator(
            name = "tariff_info_location_id_seq",
            sequenceName = "tariff_info_location_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "tariff_info_location_id_seq"
    )
    private long id;
    private String targetNameLocation; // Название локации для которой особые условия
    private float outBetAnotherLocation; // Ставка на исходящие звонки на другую локацию
    private float inBetAnotherLocation; // Ставка на входящие звонки на другую локацию

    public TariffInfoLocationEntity(){}

    public TariffInfoLocationEntity(String targetNameLocation, float outBetAnotherLocation, float inBetAnotherLocation) {
        this.targetNameLocation = targetNameLocation;
        this.outBetAnotherLocation = outBetAnotherLocation;
        this.inBetAnotherLocation = inBetAnotherLocation;
    }
}
