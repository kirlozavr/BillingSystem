package com.application.billingsystem.billing;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;
import com.application.billingsystem.entity.SubscriberEntity;
import com.application.billingsystem.file_handler.FileReaderHandler;
import com.application.billingsystem.file_handler.FileWriterHandler;
import com.application.billingsystem.mapping.SubscriberReportMapper;
import com.application.billingsystem.services.SubscriberReportService;
import com.application.billingsystem.services.SubscriberService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BillingRealTimeService implements BillingContract.BRT<SubscriberReportDto>{

    private final SubscriberService subscriberService;
    private final SubscriberReportService subscriberReportService;
    private final BillingContract.HRS contractHrs;
    private final SubscriberReportMapper subscriberReportMapper = new SubscriberReportMapper();

    @Autowired
    public BillingRealTimeService(
            SubscriberService subscriberService,
            SubscriberReportService subscriberReportService,
            @Lazy BillingContract.HRS contractHrs
    ) {
        this.subscriberService = subscriberService;
        this.subscriberReportService = subscriberReportService;
        this.contractHrs = contractHrs;
    }

    @Override
    public void run(@NotNull String filePath){
        String path = generateNewCdrPlusFileFromCdrFile(filePath);

        if (path != null){
            contractHrs.run(path);
        }
    }

    /** Метод добавляет отчет в БД и обновляет баланс пользователя **/
    @Override
    public void putAndUpdateDataToDatabase(SubscriberReportDto dto) {
        subscriberReportService
                .createSubscriberReport(subscriberReportMapper.getDtoToEntity(dto));
        updateBalance(dto);
    }

    private void updateBalance(SubscriberReportDto dto){
        SubscriberEntity subscriberEntity = subscriberService.getSubscriber(dto.getNumberPhone());
        subscriberEntity.setBalance(subscriberEntity.getBalance() - dto.getTotalCost());
        subscriberService.updateSubscriber(subscriberEntity);
    }

    /**
     * Метод генерирует CDR+ файл и возвращает path, если операция успешна, иначе null
     **/
    private String generateNewCdrPlusFileFromCdrFile(String cdrFilePath) {
        final List<CallDataRecordPlusEntity> callDataRecordPlusFinal = getListCallsAuthorizationFromCdrFile(cdrFilePath);
        String path = null;
        if (callDataRecordPlusFinal != null) {
            path = FileWriterHandler.writeCdrPlusFileAndReturnPath(callDataRecordPlusFinal);
        }
        return path;
    }

    /**
     * Метод получения получения списка CallDataRecordPlusEntity
     **/
    private List<CallDataRecordPlusEntity> getListCallsAuthorizationFromCdrFile(String cdrFilePath) {
        final List<CallDataRecordEntity> callDataRecordEntityList =
                FileReaderHandler.readCDRFileAndReturnListEntity(cdrFilePath); /** Список всех звонков из файла CDR **/

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
