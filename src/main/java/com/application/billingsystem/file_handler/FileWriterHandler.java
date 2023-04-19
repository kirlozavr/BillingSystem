package com.application.billingsystem.file_handler;

import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/** Класс отвечает за запись CDR и CDR+ файла.
 * Файлы будут каждый раз перезаписываться (при каждой тарификации файл будет обновляться). **/
public class FileWriterHandler {

    /** Метод записывает CDR File из списка CallDataRecordEntity и возвращает true если успешно или false **/
    public static boolean writeCDRFile(List<CallDataRecordEntity> callDataRecordEntityList) {
        try {
            File file = new File("\\src\\main\\resources\\cdr_file\\CDR.txt");
            FileWriter fileWriter = new FileWriter(file, false);

            for (CallDataRecordEntity entity : callDataRecordEntityList) {
                fileWriter.write(
                        String.format(
                                "%s,%s,%s,%s\n",
                                entity.getCallType(),
                                entity.getNumberPhone(),
                                entity.getStartTime(),
                                entity.getEndTime()
                        )
                );
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Метод записывает CDR+ File из списка CallDataRecordPlusEntity и возвращает true если успешно или false **/
    public static boolean writeCDRPlusFile(List<CallDataRecordPlusEntity> callDataRecordPlusEntityList) {
        try {
            File file = new File("\\src\\main\\resources\\cdr_file\\PlusCDR.txt");
            FileWriter fileWriter = new FileWriter(file, false);

            for (CallDataRecordPlusEntity entity : callDataRecordPlusEntityList) {
                fileWriter.write(
                        String.format(
                                "%s,%s,%s,%s,%s\n",
                                entity.getCallType(),
                                entity.getNumberPhone(),
                                entity.getStartTime(),
                                entity.getEndTime(),
                                entity.getTariffIndex()
                        )
                );
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
