package com.application.billingsystem.utils;

/** Класс для сравнения float **/
public class FloatCompare {

    public static boolean isEquals(float number1, float number2) {
        return Math.abs(number1 - number2) <= 0.00001;
    }
}
