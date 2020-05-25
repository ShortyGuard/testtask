package com.testtask.caloric;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Product("Pepsi",
                    "Pepsi Co",
                    100,
                    20,
                    30,
                    3000
            )));
            log.info("Preloading " + repository.save(new Product("Coca-Cola",
                    "Coca-Cola",
                    200,
                    30,
                    40,
                    2000
            )));
        };
    }
}