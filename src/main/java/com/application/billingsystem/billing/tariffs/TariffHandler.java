package com.application.billingsystem.billing.tariffs;

import com.application.billingsystem.exceptions.IncorrectArgumentException;

public class TariffHandler {

    public static Tariff getTariffByIndex(String tariffIndex){
        if (tariffIndex.equals("11")){
            return getOrdinaryTariff();
        } else if(tariffIndex.equals("06")){
            return getUnlimitedTariff();
        } else if(tariffIndex.equals("03")){
            return getPerMinuteTariff();
        } else {
            throw new IncorrectArgumentException("Неверный индекс тарифа");
        }
    }

    private static OrdinaryTariff getOrdinaryTariff(){
        return new OrdinaryTariff();
    }

    private static PerMinuteTariff getPerMinuteTariff(){
        return new PerMinuteTariff();
    }

    private static UnlimitedTariff getUnlimitedTariff(){
        return new UnlimitedTariff();
    }
}
