package com.application.billingsystem.main;

import com.application.billingsystem.configurations.TariffConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BillingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingSystemApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(TariffConfig.class);

        context.getBean(TariffConfig.class).newTariff();
    }

}
