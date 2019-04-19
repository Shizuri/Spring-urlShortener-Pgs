package com.sorsix.urlShortenerPgs.services;

import com.sorsix.urlShortenerPgs.models.ShortUrl;
import com.sorsix.urlShortenerPgs.persistencies.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrl newShortUrl(String url) {
        List<ShortUrl> shorts = getAllShorts();
        for (ShortUrl shortUrl : shorts) {
            if (shortUrl.getOriginalUrl().equals(url)) {
                return shortUrl;
            }
        }
        return shortUrlRepository.save(new ShortUrl(url));
    }

    public List<ShortUrl> getAllShorts() {
        List<ShortUrl> shorts = new ArrayList<>();
        shortUrlRepository.findAll().forEach(shorts::add);
        return shorts;
    }

    public String convertShortToOriginal(String shortUrl) {
        long shortNumber = 0L;
        shortNumber = Long.parseLong(shortUrl);
        List<ShortUrl> shorts = getAllShorts();
        ShortUrl result = null;
        for (ShortUrl s : shorts) {
            if (s.getShortUrl().equals(shortNumber)) {
                result = s;
                break;
            }
        }
        return result.getOriginalUrl();
    }
}
