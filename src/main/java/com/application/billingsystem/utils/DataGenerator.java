package com.application.billingsystem.utils;

import com.application.billingsystem.dto.SubscriberCreateDto;
import com.application.billingsystem.entity.CallDataRecordEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<SubscriberCreateDto> generateSubscribersList(int amount){
        final List<SubscriberCreateDto> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            SubscriberCreateDto subscriber = new SubscriberCreateDto(
                    generateNumberPhone(),
                    generateTariffIndex(),
                    generateBalance()
            );
            list.add(subscriber);
        }
        return list;
    }

    public static List<CallDataRecordEntity> generateCdrList(int amount) {
        final List<CallDataRecordEntity> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            String startTime = generateStartTime();
            CallDataRecordEntity callDataRecord = new CallDataRecordEntity(
                    generateCallType(),
                    generateNumberPhone(),
                    startTime,
                    generateEndTime(startTime)
            );
            list.add(callDataRecord);
        }
        return list;
    }

    private static String generateCallType() {
        byte callType = (byte) (1 + Math.random() * 2);
        return "0" + callType;
    }

    private static String generateNumberPhone() {
        long numberPhone = (long) (89991000000L + Math.random() * 9999L);
        return String.valueOf(numberPhone);
    }

    private static String generateStartTime() {
        /** Генерируется рандомно время в секундах от времени генерации до +10 дней **/
        Instant instant = Instant.ofEpochSecond(
                (long) (Instant.now().getEpochSecond()
                        + (Math.random() * 86400 * 10))
        );
        return DateMapper.getLocalDateTimeToStringInput(
                LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        );
    }

    private static String generateEndTime(String startTime) {
        /** Генерируется рандомно время в секундах от времени начала разговора до + 1 час **/
        long talkTime = (long) (1 + Math.random() * 3600);
        LocalDateTime localDateTime = DateMapper
                .getStringToLocaleDateTime(startTime).plusSeconds(talkTime);
        return DateMapper.getLocalDateTimeToStringInput(localDateTime);
    }

    private static long generateBalance() {
        return 100 * (long) (1 + Math.random() * 10);
    }

    private static String generateTariffIndex() {
        List<String> tariffIndex = new ArrayList<>(List.of("06", "03", "11"));
        byte index = (byte) (Math.random() * 3);
        return tariffIndex.get(index);
    }
}
