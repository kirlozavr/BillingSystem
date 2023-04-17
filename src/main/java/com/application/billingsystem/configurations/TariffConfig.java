package com.application.billingsystem.configurations;

import com.application.billingsystem.entity.TariffEntity;
import com.application.billingsystem.repositories.TariffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class TariffConfig {

    @Bean
    @Transactional
    public TariffEntity newTariff(){
        return new TariffEntity(
                "06",
                "Безлимит 300",
                300.0f,
                0.0f,
                1.0f,
                1.5f,
                100.0f,
                "rubles"
        );
    }

//    @Bean
//    @Transactional
//    CommandLineRunner commandLine(TariffRepository tariffRepository){
//        return args -> {
//            new TariffEntity(
//                    "06",
//                    "Безлимит 300",
//                    300.0f,
//                    0.0f,
//                    1.0f,
//                    1.5f,
//                    100.0f,
//                    "rubles"
//            );
//        };
//    }
}
