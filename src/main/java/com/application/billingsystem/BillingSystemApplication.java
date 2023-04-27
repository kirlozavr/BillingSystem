package com.application.billingsystem;

import com.application.billingsystem.billing.CallDataRecordService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BillingSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BillingSystemApplication.class, args);
        context.getBean(CallDataRecordService.class).run();
    }

}
