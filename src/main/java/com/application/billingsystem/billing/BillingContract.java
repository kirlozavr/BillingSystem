package com.application.billingsystem.billing;

import org.springframework.stereotype.Component;

/**
 * Тут я решил в эксперимент)
 * Идея взята из MVP где компоненты не обшаются друг с другом напрямую.
 * Общение происходит посредством контракта,
 * который имплементируют классы, которые должны взаимодействовать. Основной подход в андроид.
 **/

public interface BillingContract {

    interface CDR{
        void run();
    }
    interface BRT<E> {
        void run();
        void putAndUpdateDataToDatabase(E entity);
    }

    interface HRS {
        void run();
    }
}
