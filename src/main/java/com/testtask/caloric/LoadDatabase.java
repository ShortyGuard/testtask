package com.testtask.caloric;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    /**
     * Инициализация БД (можно предварительно, например, вставить тестовые данные или выполнить проверки на валидность схемы и т.п.)
     */
    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            log.info("Load database");
        };
    }
}