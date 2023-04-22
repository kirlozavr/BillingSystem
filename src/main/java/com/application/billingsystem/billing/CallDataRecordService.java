package com.application.billingsystem.billing;

import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.file_handler.FileWriterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("*/billing")
public class CallDataRecordService {

    private final BillingContract.BRT<SubscriberReportDto> contractBrt;

    @Autowired
    public CallDataRecordService(BillingContract.BRT<SubscriberReportDto> contractBrt) {
        this.contractBrt = contractBrt;
    }

    @GetMapping("/run")
    public String run() {

        List<CallDataRecordEntity> list = DataGenerator.generateCdrList(5000);
        FileWriterHandler.writeCdrFileAndReturnPath(list);
        contractBrt.run();

        return "Запущена тарификация";
    }

}
