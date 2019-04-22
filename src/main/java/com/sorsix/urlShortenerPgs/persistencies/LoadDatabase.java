package com.sorsix.urlShortenerPgs.persistencies;

import com.sorsix.urlShortenerPgs.controllers.ShortUrlController;
import com.sorsix.urlShortenerPgs.models.NewShort;
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
        NewShort youtube = new NewShort();
        youtube.newShortUrl = "https://www.youtube.com";
        NewShort javabrains = new NewShort();
        javabrains.newShortUrl = "https://javabrains.io/";

        return args -> {
            logger.info("Preloading youtube " + controller.addNewShort(youtube));
            logger.info("Preloading javabrains " + controller.addNewShort(javabrains));
        };

    }
}
