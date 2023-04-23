package com.application.billingsystem.billing;

import com.application.billingsystem.annotations.BillingInfo;
import com.application.billingsystem.dto.SubscriberReportDto;
import com.application.billingsystem.entity.CallDataRecordEntity;
import com.application.billingsystem.file_handler.FileHandler;
import com.application.billingsystem.utils.DataGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("*/billing")
@Tag(name = "Запуск биллинговой системы", description = "Позволяет запускать процесс тарификации, при запуске создаются новые звонки с помощью генератора")
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
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Запускает тарификацию")
    public void run() {
        List<CallDataRecordEntity> list = DataGenerator.generateCdrList(10000);
        fileWrite.writeCdrFileAndReturnPath(list);
        contractBrt.run();
    }

}
