package com.application.billingsystem.configurations;

import com.application.billingsystem.utils.DataGenerator;
import com.application.billingsystem.dto.SubscriberCreateDto;
import com.application.billingsystem.mapping.SubscriberMapper;
import com.application.billingsystem.repositories.SubscriberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
public class SubscriberConfig {

    @Bean
    @Transactional
    public CommandLineRunner commandLineSubscriber(SubscriberRepository repository){
        return args -> {
            SubscriberMapper mapper = new SubscriberMapper();
            List<SubscriberCreateDto> list = DataGenerator.generateSubscribersList(2000);
            list
                    .stream()
                    .map(mapper::getCreateDtoToEntity)
                    .forEach(repository::save);
        };
    }
}
