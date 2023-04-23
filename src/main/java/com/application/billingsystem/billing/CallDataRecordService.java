package com.application.billingsystem.billing;

import com.application.billingsystem.annotations.BillingInfo;
import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.file_handler.FileHandler;
import com.application.billingsystem.utils.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("*/billing")
public class CallDataRecordService implements BillingContract.CDR {

    private final BillingContract.BRT<SubscriberReportDto> contractBrt;
    private final FileHandler.Write fileWrite;

    @Autowired
    public CallDataRecordService(
            BillingContract.BRT<SubscriberReportDto> contractBrt,
            FileHandler.Write fileWrite
    ) {
        this.contractBrt = contractBrt;
        this.fileWrite = fileWrite;
    }

    @Override
    @GetMapping("/run")
    @BillingInfo("Файл CDR успешно сгенерирован")
    public void run() {
        List<CallDataRecordEntity> list = DataGenerator.generateCdrList(10000);
        fileWrite.writeCdrFileAndReturnPath(list);
        contractBrt.run();
    }

}
