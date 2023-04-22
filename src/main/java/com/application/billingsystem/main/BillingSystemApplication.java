package com.application.billingsystem.main;

import com.application.billingsystem.billing.CallDataRecordService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.application.billingsystem.entity")
@EnableJpaRepositories("com.application.billingsystem.repositories")
@ComponentScan("com.application.billingsystem")
public class BillingSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BillingSystemApplication.class, args);
        context.getBean(CallDataRecordService.class).run();
    }

}
