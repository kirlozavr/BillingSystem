package com.application.billingsystem.file_handler;

import com.application.billingsystem.annotations.FileHandlerInfo;
import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс отвечает за чтение CDR И CDR+.
 **/
@Component
public class FileReaderHandler implements FileHandler, FileHandler.Read {

    /**
     * Метод читает CDR файл и возвращает список
     **/
    @Override
    @FileHandlerInfo("CDR file read successfully")
    public List<CallDataRecordEntity> readCDRFileAndReturnListEntity() {
        List<CallDataRecordEntity> callDataRecordEntityList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(CDR_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArr = line.split("\\s\\|\\s");
                CallDataRecordEntity callDataRecordEntity = new CallDataRecordEntity(
                        lineArr[0],
                        lineArr[1],
                        lineArr[2],
                        lineArr[3]
                );
                callDataRecordEntityList.add(callDataRecordEntity);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return callDataRecordEntityList;
    }

    /**
     * Метод читает CDR+ файл и возвращает список
     **/
    @Override
    @FileHandlerInfo("CDR+ file read successfully")
    public List<CallDataRecordPlusEntity> readCDRPlusFileAndReturnListEntity() {
        List<CallDataRecordPlusEntity> callDataRecordPlusEntityList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(CDR_PLUS_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArr = line.split("\\s\\|\\s");
                CallDataRecordPlusEntity callDataRecordPlusEntity = new CallDataRecordPlusEntity(
                        lineArr[0],
                        lineArr[1],
                        lineArr[2],
                        lineArr[3],
                        lineArr[4]
                );
                callDataRecordPlusEntityList.add(callDataRecordPlusEntity);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return callDataRecordPlusEntityList;
    }
}
