package com.application.billingsystem.file_handler;

import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Класс отвечает за запись CDR и CDR+ файла.
 **/
public class FileWriterHandler extends FileHandler {

    /**
     * Метод записывает CDR File из списка CallDataRecordEntity и возвращает true если успешно или false
     **/
    public static boolean writeCdrFileAndReturnPath(List<CallDataRecordEntity> callDataRecordEntityList) {
        try {
            File file = new File(CDR_FILE_PATH);
            FileWriter fileWriter = new FileWriter(file, false);

            for (CallDataRecordEntity entity : callDataRecordEntityList) {
                fileWriter.write(
                        String.format(
                                "%s | %s | %s | %s\n",
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

    /**
     * Метод записывает CDR+ File из списка CallDataRecordPlusEntity и возвращает true если успешно или false
     **/
    public static boolean writeCdrPlusFileAndReturnPath(List<CallDataRecordPlusEntity> callDataRecordPlusEntityList) {
        try {
            File file = new File(CDR_PLUS_FILE_PATH);
            FileWriter fileWriter = new FileWriter(file, false);

            for (CallDataRecordPlusEntity entity : callDataRecordPlusEntityList) {
                fileWriter.write(
                        String.format(
                                "%s | %s | %s | %s | %s\n",
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
