package com.application.billingsystem.file_handler;

import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс отвечает за чтение CDR И CDR+.
 **/
public class FileReaderHandler extends FileHandler {


    /**
     * Метод читает CDR файл и возвращает список
     **/
    public static List<CallDataRecordEntity> readCDRFileAndReturnListEntity(String path) {
        List<CallDataRecordEntity> callDataRecordEntityList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArr = line.split(",");
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
    public static List<CallDataRecordPlusEntity> readCDRPlusFileAndReturnListEntity(String path) {
        List<CallDataRecordPlusEntity> callDataRecordPlusEntityList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArr = line.split(",");
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
