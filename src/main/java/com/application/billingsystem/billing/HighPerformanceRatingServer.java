package com.application.billingsystem.billing;

import com.application.billingsystem.annotations.BillingInfo;
import com.application.billingsystem.billing.tariffs.Tariff;
import com.application.billingsystem.billing.tariffs.TariffHandler;
import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.*;
import com.application.billingsystem.file_handler.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HighPerformanceRatingServer implements BillingContract.HRS {

    private final BillingContract.BRT<SubscriberReportDto> contractBrt;
    private final FileHandler.Read fileRead;

    @Autowired
    public HighPerformanceRatingServer(
            BillingContract.BRT<SubscriberReportDto> contractBrt,
            FileHandler.Read fileRead
    ) {
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
        /** Список CDR+ сущностей, где ключ - номер телефона абонента **/
        final Map<String, DataListEntity<CallDataRecordPlusEntity>> cdrPlusHashMap = new HashMap<>();
        /** Читаем CDR+ и получаем информацию в списке **/
        final List<CallDataRecordPlusEntity> callDataRecordPlusEntityList =
                fileRead.readCDRPlusFileAndReturnListEntity();

        if (!callDataRecordPlusEntityList.isEmpty()) {
            for (CallDataRecordPlusEntity entity : callDataRecordPlusEntityList) {

                /** Тут CallDataRecordPlusEntity групируются по номеру телефона и заносятся в мапу **/
                if (cdrPlusHashMap.containsKey(entity.getNumberPhone())) {
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
        generateSubscriberReports(cdrPlusHashMap);
    }

    /**
     * Метод формирует SubscriberReportEntity,
     * который содержит результат тарификации и отправляет его обратно в BRT
     **/
    private void generateSubscriberReports(
            Map<String, DataListEntity<CallDataRecordPlusEntity>> cdrPlus
    ) {
        for (Map.Entry<String, DataListEntity<CallDataRecordPlusEntity>> entryMap : cdrPlus.entrySet()) {
            Tariff tariff = TariffHandler
                    .getTariffByIndex(entryMap.getValue().getList().get(0).getTariffIndex());
            contractBrt.putAndUpdateDataToDatabase(
                    generateSubscriberReportByTariff(
                            entryMap.getValue().getList(),
                            tariff
                    )
            );
        }
    }

    /**
     * Метод формирует отчет, исходя из тарифа абонента.
     **/
    private SubscriberReportDto generateSubscriberReportByTariff(
            List<CallDataRecordPlusEntity> entityList,
            Tariff tariff
    ) {
        return tariff.calculate(entityList);
    }
}

