package com.application.billingsystem.file_handler;

import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Класс отвечает за запись CDR и CDR+ файла. Изначально хотел перезаписывать файл,
 * но решил при каждой тарификации создавать новый с индексом + 1
 **/
public class FileWriterHandler extends FileHandler {

    /**
     * Метод записывает CDR File из списка CallDataRecordEntity и возвращает path если успешно или null
     **/
    public static String writeCdrFileAndReturnPath(List<CallDataRecordEntity> callDataRecordEntityList) {
        try {
            String path = CDR_FILE_PATH + getNextFileIndex(CDR_FILE_PATH) + ".txt";
            File file = new File(path);
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
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод записывает CDR+ File из списка CallDataRecordPlusEntity и возвращает path если успешно или null
     **/
    public static String writeCdrPlusFileAndReturnPath(List<CallDataRecordPlusEntity> callDataRecordPlusEntityList) {
        try {
            String path = CDR_PLUS_FILE_PATH + getNextFileIndex(CDR_PLUS_FILE_PATH) + ".txt";
            File file = new File(path);
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
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
