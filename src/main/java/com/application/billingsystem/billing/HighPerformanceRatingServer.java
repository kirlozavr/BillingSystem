package com.application.billingsystem.billing;

import com.application.billingsystem.annotations.BillingInfo;
import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.*;
import com.application.billingsystem.file_handler.FileHandler;
import com.application.billingsystem.utils.DateMapper;
import com.application.billingsystem.utils.FloatCompare;
import com.application.billingsystem.mapping.SubscriberReportMapper;
import com.application.billingsystem.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HighPerformanceRatingServer implements BillingContract.HRS {

    private final TariffService tariffService;
    private final SubscriberReportMapper mapper = new SubscriberReportMapper();
    private final BillingContract.BRT<SubscriberReportDto> contractBrt;
    private final FileHandler.Read fileRead;

    @Autowired
    public HighPerformanceRatingServer(
            TariffService tariffService,
            BillingContract.BRT<SubscriberReportDto> contractBrt,
            FileHandler.Read fileRead
    ) {
        this.tariffService = tariffService;
        this.contractBrt = contractBrt;
        this.fileRead = fileRead;
    }

    @Override
    @BillingInfo("Запущена сортировка звонков")
    public void run() {
        sortingSubscriberCalls();
    }

    /**
     * Метод сортирует CDR+ по абонентам
     **/
    private void sortingSubscriberCalls() {
        /** Кэш тарифов, чтобы лишний раз не обращаться в БД, где ключ - индекс тарифа **/
        final Map<String, TariffEntity> tariffEntityHashMap = new HashMap<>();
        /** Список CDR+ сущностей, где ключ - номер телефона абонента **/
        final Map<String, DataListEntity<CallDataRecordPlusEntity>> cdrPlusHashMap = new HashMap<>();
        /** Читаем CDR+ и получаем информацию в списке **/
        final List<CallDataRecordPlusEntity> callDataRecordPlusEntityList =
                fileRead.readCDRPlusFileAndReturnListEntity();

        TariffEntity tariff;

        if (!callDataRecordPlusEntityList.isEmpty()) {
            for (CallDataRecordPlusEntity entity : callDataRecordPlusEntityList) {

                /** Те тарифы, которых нет в локальном кэше, добавляются туда,
                 * же тариф уже есть, то просто берем из кэша **/
                if (tariffEntityHashMap.containsKey(entity.getTariffIndex())) {
                    tariff = tariffEntityHashMap.get(entity.getTariffIndex());
                } else {
                    tariff = tariffService.getByIndex(entity.getTariffIndex());
                    tariffEntityHashMap.put(tariff.getTariffIndex(), tariff);
                }

                /** Тут CallDataRecordPlusEntity групируются по номеру телефона и заносятся в мапу **/
                if(cdrPlusHashMap.containsKey(entity.getNumberPhone())){
                    cdrPlusHashMap
                            .get(entity.getNumberPhone())
                            .addEntity(entity);
                } else {
                    cdrPlusHashMap.put(
                            entity.getNumberPhone(),
                            new DataListEntity<CallDataRecordPlusEntity>().addEntity(entity)
                    );
                }
            }
        }
        generateSubscriberReports(cdrPlusHashMap, tariffEntityHashMap);
    }

    /**
     * Метод формирует SubscriberReportEntity,
     * который содержит результат тарификации и отправляет его обратно в BRT
     **/
    private void generateSubscriberReports(
            Map<String, DataListEntity<CallDataRecordPlusEntity>> cdrPlus,
            Map<String, TariffEntity> tariffs
    ) {
        for (Map.Entry<String, DataListEntity<CallDataRecordPlusEntity>> entryMap : cdrPlus.entrySet()) {
            contractBrt.putAndUpdateDataToDatabase(
                    calculateSubscriberReportByTariff(
                            entryMap.getValue().getList(),
                            tariffs.get(entryMap.getValue().getList().get(0).getTariffIndex()))
            );
        }
    }

    /**
     * Метод производит тарификацию абонента.
     * Метод расчитан на универсальность,
     * но работает относительно тех тарифов, что даны по условию.
     * Мне очень понравилась идея, для каждого тарифа создавать свой класс,
     * в котором будет производиться расчет, на вход он будет принимать List<CallDataRecordPlusEntity> entityList,
     * а возвращать уже расчитанный отчет. Но т.к. сразу эта идея не пришла в голову, реализовал так.
     **/
    private SubscriberReportDto calculateSubscriberReportByTariff(
            List<CallDataRecordPlusEntity> entityList,
            TariffEntity tariff
    ) {
        final String numberPhone = entityList.get(0).getNumberPhone();
        final String tariffIndex = tariff.getTariffIndex();
        final String monetaryUnit = tariff.getMonetaryUnit();
        float totalCost = 0.0f;
        int totalMinute = 0;

        SubscriberReportEntity subscriberReport =
                new SubscriberReportEntity(
                        numberPhone,
                        tariffIndex,
                        totalCost,
                        monetaryUnit
                );

        for (CallDataRecordPlusEntity entity : entityList) {
            float cost; // Стоимость одного звонка
            int minute = DateMapper.getDurationToMinute(
                    entity.getStartTime(),
                    entity.getEndTime()
            ); // Продолжительность одного звонка
            int minutesBefore; // Продолжительность минут до превышения лимита
            int minutesAfter; // Продолжительность минут после превышения лимита

            if(totalMinute >= tariff.getMinuteLimit()){
                minutesAfter = minute;
                minutesBefore = 0;
            } else if ((totalMinute + minute) >= tariff.getMinuteLimit()){
                minutesAfter = (totalMinute + minute) - tariff.getMinuteLimit();
                minutesBefore = minute - minutesAfter;
            } else {
                minutesBefore = minute;
                minutesAfter = 0;
            }

            if (entity.getCallType().equals("01")) { // Проверка на тип звонка: входящий / исходящий
                /** Стоимость звонка считается с учетом превышен ли лимит разговоров или нет.
                 * Пример 1: если безлимит, то до превышения лимита стоимость = 0р/м, т.к. tariff.get...BetBeforeLimit = 0,
                 * если после превышения лимита, то стоимость = 1р/м, т.к. tariff.get...BetAfterLimit = 1 по условию.
                 * Пример 2: если поминутный, то tariff.getMinuteLimit() = 0, соответственно стоимость считается
                 * по параметру tariff.get...BetAfterLimit, который = 1.5 **/
                cost = (minutesAfter * tariff.getOutBetAfterLimit()) + (minutesBefore * tariff.getOutBetBeforeLimit());
                totalMinute += minute;
            } else {
                cost = (minutesAfter * tariff.getInBetAfterLimit()) + (minutesBefore * tariff.getInBetBeforeLimit());

                /** Тут идет оптимизация под тариф Обычный, т.к. входящие звонки бесплатные,
                 * соответственно как я понимаю их учитывать в лимит не стоит. **/
                if (
                        !(FloatCompare.isEquals(tariff.getInBetAfterLimit(), tariff.getInBetBeforeLimit())
                                && FloatCompare.isEquals(tariff.getInBetBeforeLimit(), 0.0f))
                ) {
                    totalMinute += minute;
                }
            }

            totalCost += cost;
            subscriberReport.addPayload(
                    new PayloadEntity(
                            entity.getCallType(),
                            DateMapper.getStringToDateTimeString(entity.getStartTime()),
                            DateMapper.getStringToDateTimeString(entity.getEndTime()),
                            DateMapper.getDurationToString(entity.getStartTime(), entity.getEndTime()),
                            cost
                    )
            );
        }
        /** Дополнительное начисление к конечной стоимости исходя из абонентской платы **/
        totalCost += tariff.getSubscriberPayment();
        subscriberReport.setTotalCost(totalCost);

        return mapper.getEntityToDto(subscriberReport);
    }

    /** Тут будет новая логика расчета услуг **/
    private SubscriberReportDto calculateSubscriberReportByTariff(List<CallDataRecordPlusEntity> entityList) {
        return null;
    }
}

