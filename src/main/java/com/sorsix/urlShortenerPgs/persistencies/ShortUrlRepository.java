package com.sorsix.urlShortenerPgs.persistencies;

import com.sorsix.urlShortenerPgs.models.ShortUrl;
import org.springframework.data.repository.CrudRepository;

public interface ShortUrlRepository extends CrudRepository<ShortUrl, Long> {
}
