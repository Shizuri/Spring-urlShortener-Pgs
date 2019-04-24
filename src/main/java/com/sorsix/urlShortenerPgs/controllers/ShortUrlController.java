package com.sorsix.urlShortenerPgs.controllers;

import com.sorsix.urlShortenerPgs.models.ShortUrl;
import com.sorsix.urlShortenerPgs.services.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/shorturl/")
public class ShortUrlController {

    static final private Logger logger = LoggerFactory.getLogger(ShortUrlController.class);

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping
    public ResponseEntity addNewShort(@RequestBody String url) {
        try {
            return ResponseEntity.ok().body(shortUrlService.newShortUrl(url));
        } catch (Exception exc) {
            logger.warn("Probable invalid URL with exception [{}]", exc.getMessage());
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", "Invalid URL"));
        }

    }

    @GetMapping
    public List<ShortUrl> getAllShorts() {
        return shortUrlService.getAllShorts();
    }

    @GetMapping(value = "/{shortUrl}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShortUrl(@PathVariable String shortUrl, HttpServletResponse response) {
        try {
            response.sendRedirect(shortUrlService.convertShortToOriginal(shortUrl));
            return null;
        } catch (Exception exc) {
            logger.warn("There was an exception [{}]", exc.getMessage());
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", exc.getMessage()));
        }

    }

}
