package com.sorsix.urlShortenerPgs.persistencies;

import com.sorsix.urlShortenerPgs.models.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
}
