package com.application.billingsystem.billing;

import com.application.billingsystem.annotations.BillingInfo;
import com.application.billingsystem.controllers.SubscriberReportController;
import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;
import com.application.billingsystem.entity.SubscriberEntity;
import com.application.billingsystem.file_handler.FileHandler;
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
    private final FileHandler.Write fileWriter;
    private final FileHandler.Read fileRead;

    @Autowired
    public BillingRealTimeService(
            SubscriberService subscriberService,
            SubscriberReportController subscriberReportController,
            @Lazy BillingContract.HRS contractHrs,
            FileHandler.Write fileWriter,
            FileHandler.Read fileRead
    ) {
        this.subscriberService = subscriberService;
        this.subscriberReportController = subscriberReportController;
        this.contractHrs = contractHrs;
        this.fileWriter = fileWriter;
        this.fileRead = fileRead;
    }

    @Override
    @BillingInfo("Запущена тарификация в HRS")
    public void run(){
        boolean isGenerate = generateNewCdrPlusFileFromCdrFile();
        if (isGenerate){
            contractHrs.run();
        }
    }

    /** Метод добавляет отчет в БД и обновляет баланс пользователя,
     * если у totalCost есть дробная часть, она округляется в большую сторону и списывается со счета абонента,
     * например: 70.3 -> 71, 70.5 -> 71, 70.8 -> 71
     * **/
    @Override
    public void putAndUpdateDataToDatabase(SubscriberReportDto dto) {
        subscriberReportController.post(dto);
    }

    /**
     * Метод генерирует CDR+ файл и возвращает path, если операция успешна, иначе null
     **/
    private boolean generateNewCdrPlusFileFromCdrFile() {
        final List<CallDataRecordPlusEntity> callDataRecordPlusFinal = getListCallsAuthorizationFromCdrFile();

        if (callDataRecordPlusFinal != null) {
            fileWriter.writeCdrPlusFileAndReturnPath(callDataRecordPlusFinal);
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
                fileRead.readCDRFileAndReturnListEntity(); /** Список всех звонков из файла CDR **/

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
                        .getByNumberPhoneAndPositiveBalance(numberPhone);
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
