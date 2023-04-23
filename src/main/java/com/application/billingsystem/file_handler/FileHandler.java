package com.application.billingsystem.file_handler;

import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.entity.CallDataRecordPlusEntity;

import java.util.List;

public interface FileHandler {
    String CDR_FILE_PATH = ".\\src\\main\\resources\\cdr_file\\CDR.txt";
    String CDR_PLUS_FILE_PATH = ".\\src\\main\\resources\\cdr_file\\PLUS_CDR.txt";
    interface Read{
        List<CallDataRecordEntity> readCDRFileAndReturnListEntity();
        List<CallDataRecordPlusEntity> readCDRPlusFileAndReturnListEntity();
    }
    interface Write{
        boolean writeCdrFileAndReturnPath(List<CallDataRecordEntity> callDataRecordEntityList);
        boolean writeCdrPlusFileAndReturnPath(List<CallDataRecordPlusEntity> callDataRecordPlusEntityList);
    }
}
