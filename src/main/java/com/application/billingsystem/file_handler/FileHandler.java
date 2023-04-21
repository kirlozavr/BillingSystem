package com.application.billingsystem.file_handler;

import java.io.File;

public abstract class FileHandler {

    protected static final String CDR_FILE_PATH = "\\src\\main\\resources\\cdr_file\\CDR_";
    protected static final String CDR_PLUS_FILE_PATH = "\\src\\main\\resources\\cdr_file\\PLUS_CDR_";

    protected static int getNextFileIndex(String path){
        int count = 1;
        while (!new File(path + count + ".txt").exists()){
            count++;
        }
        return count + 1;
    }
}
