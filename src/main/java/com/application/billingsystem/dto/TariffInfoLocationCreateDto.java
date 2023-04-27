package com.application.billingsystem.dto;

import lombok.Data;

@Data
public class TariffInfoLocationCreateDto {
    private float outBetAnotherLocation; // Ставка на исходящие звонки на другую локацию
    private float inBetAnotherLocation; // Ставка на входящие звонки на другую локацию
}
