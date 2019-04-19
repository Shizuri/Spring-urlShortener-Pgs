package com.sorsix.urlShortenerPgs.services;

import com.sorsix.urlShortenerPgs.models.ShortUrl;
import com.sorsix.urlShortenerPgs.persistencies.ShortUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShortUrlService {

    static final Logger logger = LoggerFactory.getLogger(ShortUrlService.class);

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrl newShortUrl(String url) {
        List<ShortUrl> shorts = getAllShorts();
        for (ShortUrl shortUrl : shorts) {
            if (shortUrl.getOriginalUrl().equals(url)) {
                logger.info("Found DUPLICATE URL: [{}]. WIll not create a new one", shortUrl);
                return shortUrl;
            }
        }
        logger.info("Saving URL: [{}]", url);
        return shortUrlRepository.save(new ShortUrl(url));
    }

    public List<ShortUrl> getAllShorts() {
        return shortUrlRepository.findAll();
    }

    public String convertShortToOriginal(String shortUrl) {
        long shortNumber = Long.parseLong(shortUrl);
        List<ShortUrl> shorts = getAllShorts();
        ShortUrl result = null;
        for (ShortUrl s : shorts) {
            if (s.getShortUrl().equals(shortNumber)) {
                logger.info("Was looking for value [{}], found [{}]", shortUrl, s);
                result = s;
                break;
            }
        }
        if(result == null){
            logger.info("Didn't find an entry for the shortURL: [{}]", shortUrl);
        }
        return result.getOriginalUrl();
    }
}
