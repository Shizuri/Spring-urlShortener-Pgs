package com.sorsix.urlShortenerPgs.persistencies;

import com.sorsix.urlShortenerPgs.models.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    ShortUrl findByOriginalUrl(String originalUrl);

    ShortUrl findByShortUrl(Long shortUrl);

    @Query("select max(su.shortUrl) from ShortUrl su")
    Long getMaxShortUrl();


}
