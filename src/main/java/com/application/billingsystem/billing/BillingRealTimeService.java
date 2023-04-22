package com.application.billingsystem.billing;

import com.application.billingsystem.controllers.SubscriberReportController;
import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;
import com.application.billingsystem.entity.SubscriberEntity;
import com.application.billingsystem.file_handler.FileReaderHandler;
import com.application.billingsystem.file_handler.FileWriterHandler;
import com.application.billingsystem.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BillingRealTimeService implements BillingContract.BRT<SubscriberReportDto>{

    private final SubscriberService subscriberService;
    private final SubscriberReportController subscriberReportController;
    private final BillingContract.HRS contractHrs;

    @Autowired
    public BillingRealTimeService(
            SubscriberService subscriberService,
            SubscriberReportController subscriberReportController,
            @Lazy BillingContract.HRS contractHrs
    ) {
        this.subscriberService = subscriberService;
        this.subscriberReportController = subscriberReportController;
        this.contractHrs = contractHrs;
    }

    @Override
    public void run(){
        boolean isGenerate = generateNewCdrPlusFileFromCdrFile();
        if (isGenerate){
            contractHrs.run();
        }
    }

    /** Метод добавляет отчет в БД и обновляет баланс пользователя **/
    @Override
    public void putAndUpdateDataToDatabase(SubscriberReportDto dto) {
        subscriberReportController
                .postSubscriberReport(dto);
    }

    /**
     * Метод генерирует CDR+ файл и возвращает path, если операция успешна, иначе null
     **/
    private boolean generateNewCdrPlusFileFromCdrFile() {
        final List<CallDataRecordPlusEntity> callDataRecordPlusFinal = getListCallsAuthorizationFromCdrFile();

        if (callDataRecordPlusFinal != null) {
            FileWriterHandler.writeCdrPlusFileAndReturnPath(callDataRecordPlusFinal);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод получения получения списка CallDataRecordPlusEntity
     **/
    private List<CallDataRecordPlusEntity> getListCallsAuthorizationFromCdrFile() {
        final List<CallDataRecordEntity> callDataRecordEntityList =
                FileReaderHandler.readCDRFileAndReturnListEntity(); /** Список всех звонков из файла CDR **/

        if (!callDataRecordEntityList.isEmpty()) {
            /** Набор уникальных номеров, которые осуществляли разговор
             (чтобы не обращаться к БД лишний раз) **/
            final Set<String> callDataRecordEntitySet = callDataRecordEntityList
                    .stream()
                    .map(CallDataRecordEntity::getNumberPhone)
                    .collect(Collectors.toSet());

            final Map<String, SubscriberEntity> subscriberAuthorization = new HashMap<>();

            /** Получаем из БД сущность если баланс пользователя больше 0, иначе Null.
             * И записываем в карту, где ключ - номер телефона **/
            for (String numberPhone : callDataRecordEntitySet) {
                SubscriberEntity subscriber = subscriberService
                        .getSubscriberByNumberPhoneAndPositiveBalance(numberPhone);
                if (subscriber != null) {
                    subscriberAuthorization.put(subscriber.getNumberPhone(), subscriber);
                }
            }

            /** Создаем конечный список CDR+ путем фильтрации callDataRecordEntityList
             * по номерам авторизованных абонентов,
             * а затем мапим в сущность CallDataRecordPlusEntity **/
            List<CallDataRecordPlusEntity> callDataRecordPlusFinal =
                    callDataRecordEntityList
                            .stream()
                            .filter(entity ->
                                    subscriberAuthorization
                                            .containsKey(entity.getNumberPhone())
                            ).map(entity ->
                                    mapCDRToCDRPlus(
                                            entity,
                                            subscriberAuthorization.get(entity.getNumberPhone()).getTariffIndex()
                                    )
                            ).collect(Collectors.toList());

            return callDataRecordPlusFinal;
        } else {
            return null;
        }
    }

    private CallDataRecordPlusEntity mapCDRToCDRPlus(
            CallDataRecordEntity callDataRecordEntity,
            String tariffIndex
    ) {
        return new CallDataRecordPlusEntity(callDataRecordEntity, tariffIndex);
    }

}
