package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class TariffInfoOperatorDto {
    private final long id;
    private final float outBetAnotherOperator; // Ставка на исходящие звонки другому оператору
    private final float inBetAnotherOperator; // Ставка на входящие звонки другому оператору
}
