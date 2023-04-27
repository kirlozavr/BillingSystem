package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class TariffInfoOperatorCreateDto {
    private float outBetAnotherOperator; // Ставка на исходящие звонки другому оператору
    private float inBetAnotherOperator; // Ставка на входящие звонки другому оператору
}
