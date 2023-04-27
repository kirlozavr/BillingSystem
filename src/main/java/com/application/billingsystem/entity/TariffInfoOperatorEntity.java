package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tariff_info_operator")
public class TariffInfoOperatorEntity {

    @Id
    @SequenceGenerator(
            name = "tariff_info_operator_id_seq",
            sequenceName = "tariff_info_operator_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "tariff_info_operator_id_seq"
    )
    private long id;
    private String targetNameOperator; // Название оператора для которого особые условия
    private float outBetAnotherOperator; // Ставка на исходящие звонки другому оператору
    private float inBetAnotherOperator; // Ставка на входящие звонки другому оператору

    public TariffInfoOperatorEntity(){}

    public TariffInfoOperatorEntity(String targetNameOperator, float outBetAnotherOperator, float inBetAnotherOperator) {
        this.targetNameOperator = targetNameOperator;
        this.outBetAnotherOperator = outBetAnotherOperator;
        this.inBetAnotherOperator = inBetAnotherOperator;
    }
}
