package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class TariffInfoLocationDto {
    private final long id;
    private final String targetNameLocation;
    private final float outBetAnotherLocation; // Ставка на исходящие звонки на другую локацию
    private final float inBetAnotherLocation; // Ставка на входящие звонки на другую локацию
}
