package com.application.billingsystem.utils;

import com.application.billingsystem.exceptions.IncorrectArgumentException;

import java.util.regex.Pattern;

/** Класс для проверки корректности введенных данных **/
public class ValidationUtils {

    public static void checkId(long id){
        if(id < 0){
            throw new IncorrectArgumentException("Id не может быть меньше 1");
        }
    }

    public static void checkIndex(String index){
        if(index == null){
            throw new IncorrectArgumentException("Index не указан");
        }
    }

    /** Понятное дело, что код номера бывает разный и соответственно длина номера разная,
     * плюсом есть спец. символы, но в рамках этого задания сделана упрощенная проверка **/
    public static void checkNumberPhone(String numberPhone){
        var regex = "\\d{11}";
        var pattern = Pattern.compile(regex);
        if(!pattern.matcher(numberPhone).matches()){
            throw new IncorrectArgumentException("Номер телефона не может быть меньше 11 символов и должен состоять из цифр");
        }
    }

    public static void checkPayment(long money){
        if (money < 1){
            throw new IncorrectArgumentException("Платеж не может быть меньше 1");
        }
    }
}
