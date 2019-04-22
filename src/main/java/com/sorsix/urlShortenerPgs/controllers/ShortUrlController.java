package com.sorsix.urlShortenerPgs.controllers;

import com.sorsix.urlShortenerPgs.models.Error;
import com.sorsix.urlShortenerPgs.models.NewShort;
import com.sorsix.urlShortenerPgs.models.ShortUrl;
import com.sorsix.urlShortenerPgs.services.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
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
    public ResponseEntity addNewShort(@RequestBody NewShort newShort) {
        try {
            new URL(newShort.newShortUrl).toURI();
            return ResponseEntity.ok().body(shortUrlService.newShortUrl(newShort.newShortUrl));
        } catch (Exception exc) {
            logger.warn("Probable invalid URL with exception [{}]", exc.getMessage());
            return ResponseEntity.badRequest().body(new Error("Invalid URL"));
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
            if (exc.getMessage() == null) {
                return ResponseEntity.badRequest().body(new Error("No short url found for given input"));
            }
            if (exc.getMessage().contains("For input string")) {
                return ResponseEntity.badRequest().body(new Error("Wrong Format"));
            }
            return ResponseEntity.badRequest().body(new Error(exc.getMessage()));
        }

    }

}
