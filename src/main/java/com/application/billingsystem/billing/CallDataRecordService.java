package com.application.billingsystem.billing;

import com.application.billingsystem.dto.SubscriberReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class CallDataRecordService {

    private final BillingContract.BRT<SubscriberReportDto> contractBrt;

    @Autowired
    public CallDataRecordService(BillingContract.BRT<SubscriberReportDto> contractBrt) {
        this.contractBrt = contractBrt;
    }

    @RequestMapping(value = "*/billing/run", method = RequestMethod.PATCH)
    public void run(){
        String path = null;
        contractBrt.run(path);
    }

}
