package com.sorsix.urlShortenerPgs.persistencies;

import com.sorsix.urlShortenerPgs.controllers.ShortUrlController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    static final private Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ShortUrlController controller){
        return args -> {
            logger.info("Preloading example " + controller.addNewShort("https://www.example.com"));
            logger.info("Preloading javabrains " + controller.addNewShort("https://javabrains.io/"));
        };

    }
}
