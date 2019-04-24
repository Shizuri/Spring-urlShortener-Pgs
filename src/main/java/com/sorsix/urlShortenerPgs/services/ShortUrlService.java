package com.sorsix.urlShortenerPgs.services;

import com.sorsix.urlShortenerPgs.models.ShortUrl;
import com.sorsix.urlShortenerPgs.persistencies.ShortUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShortUrlService {

    static final private Logger logger = LoggerFactory.getLogger(ShortUrlService.class);
    private final ShortUrlRepository shortUrlRepository;
    private long counter = 0L;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrl newShortUrl(String url) {
        if(shortUrlRepository.getMaxShortUrl() != null){
            counter = shortUrlRepository.getMaxShortUrl();
        }

        ShortUrl shortUrl = shortUrlRepository.findByOriginalUrl(url);
        if(shortUrl != null){
            logger.info("Found DUPLICATE URL: [{}]. Will not create a new one", shortUrl);
            return shortUrl;
        }

        logger.info("Saving URL: [{}]", url);
        return shortUrlRepository.save(new ShortUrl(++counter, url));
    }

    public List<ShortUrl> getAllShorts() {
        return shortUrlRepository.findAll();
    }

    @Transactional
    public String convertShortToOriginal(String shortUrl) throws Exception{
        ShortUrl shortUrlFromRepo = null;
        try {
            shortUrlFromRepo = shortUrlRepository.findByShortUrl(Long.parseLong(shortUrl));
        }catch (Exception exc){
            logger.warn("[{}] is not valid input", shortUrl);
            throw new Exception(shortUrl + " is not valid input");
        }

        if(shortUrlFromRepo == null){
            logger.warn("Didn't find an entry for the shortURL: [{}]", shortUrl);
            throw new Exception("No short url found for: " + shortUrl);
        } else {
            shortUrlFromRepo.setNumberOfVisits(shortUrlFromRepo.getNumberOfVisits() + 1);
            return shortUrlFromRepo.getOriginalUrl();
        }
    }
}
