package com.application.billingsystem.main;

/** Класс для сравнения float **/
public class FloatCompare {

    public static boolean isEquals(float number1, float number2) {
        if (Math.abs(number1 - number2) <= 0.00001)
            return true;
        else
            return false;
    }
}
