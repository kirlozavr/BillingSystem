package com.application.billingsystem.configurations;

import com.application.billingsystem.entity.TariffEntity;
import com.application.billingsystem.entity.TariffInfoEntity;
import com.application.billingsystem.entity.TariffInfoOperatorEntity;
import com.application.billingsystem.repositories.TariffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class TariffConfig {

    @Bean
    @Transactional
    public CommandLineRunner commandLineTariff(TariffRepository tariffRepository) {
        return args -> {
            tariffRepository.save(new TariffEntity(
                    "06",
                    "Безлимит 300",
                    "Ромашка",
                    "Russia",
                    "rubles",
                    new TariffInfoEntity(
                            300,
                            0.0f,
                            1.0f,
                            0.0f,
                            1.0f,
                            100,
                            null,
                            null
                    )
            ));
            tariffRepository.save(new TariffEntity(
                    "03",
                    "Поминутный",
                    "Ромашка",
                    "Russia",
                    "rubles",
                    new TariffInfoEntity(
                            0,
                            0.0f,
                            1.5f,
                            0.0f,
                            1.5f,
                            0,
                            null,
                            null
                    )
            ));
            tariffRepository.save(new TariffEntity(
                    "11",
                    "Обычный",
                    "Ромашка",
                    "Russia",
                    "rubles",
                    new TariffInfoEntity(
                            100,
                            0.5f,
                            1.5f,
                            0.0f,
                            0.0f,
                            0,
                            null,
                            null
                    )
            ));

            List<TariffInfoOperatorEntity> operatorEntities = new ArrayList<>();
            operatorEntities.add(
                    new TariffInfoOperatorEntity(
                            "Ромашка",
                            0.0f,
                            0.0f
                    ));
            tariffRepository.save(new TariffEntity(
                    "01",
                    "Тариф Х",
                    "Ромашка",
                    "Russia",
                    "rubles",
                    new TariffInfoEntity(
                            100,
                            0.0f,
                            1.5f,
                            0.0f,
                            1.5f,
                            0,
                            operatorEntities,
                            null
                    )
            ));
        };
    }
}
