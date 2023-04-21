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
    public CommandLineRunner commandLine(TariffRepository tariffRepository){
        return args -> {
            tariffRepository.save(new TariffEntity(
                    "06",
                    "Безлимит 300",
                    300,
                    0.0f,
                    1.0f,
                    0.0f,
                    1.0f,
                    100,
                    "rubles"
            ));
            tariffRepository.save(new TariffEntity(
                    "03",
                    "Поминутный",
                    0,
                    0.0f,
                    1.5f,
                    0.0f,
                    1.5f,
                    0,
                    "rubles"
            ));
            tariffRepository.save(new TariffEntity(
                    "11",
                    "Обычный",
                    100,
                    0.5f,
                    1.5f,
                    0.0f,
                    0.0f,
                    0,
                    "rubles"
            ));
        };
    }
}
