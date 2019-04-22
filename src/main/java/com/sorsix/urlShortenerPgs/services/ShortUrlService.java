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

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrl newShortUrl(String url) {
        List<ShortUrl> shorts = getAllShorts();
        for (ShortUrl shortUrl : shorts) {
            if (shortUrl.getOriginalUrl().equals(url)) {
                logger.warn("Found DUPLICATE URL: [{}]. Will not create a new one", shortUrl);
                return shortUrl;
            }
        }
        logger.info("Saving URL: [{}]", url);
        return shortUrlRepository.save(new ShortUrl(url));
    }

    public List<ShortUrl> getAllShorts() {
        return shortUrlRepository.findAll();
    }

    @Transactional
    public String convertShortToOriginal(String shortUrl) {
        long shortNumber = Long.parseLong(shortUrl);
        List<ShortUrl> shorts = getAllShorts();
        ShortUrl result = null;
        for (ShortUrl s : shorts) {
            if (s.getShortUrl().equals(shortNumber)) {
                logger.info("Was looking for value [{}], found [{}]", shortUrl, s);
                result = s;
                shortUrlRepository.findById(s.getShortUrl()).map(x -> {
                    x.setNumberOfVisits(x.getNumberOfVisits() + 1);
                    logger.info("Number of visits on site [{}] is [{}]", s.getOriginalUrl(), s.getNumberOfVisits());
                    return x;
                });
            }
        }
        if (result == null) {
            logger.info("Didn't find an entry for the shortURL: [{}]", shortUrl);
        }
        return result.getOriginalUrl();
    }
}
