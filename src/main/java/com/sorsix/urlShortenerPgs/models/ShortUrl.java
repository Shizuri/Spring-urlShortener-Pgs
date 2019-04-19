package com.sorsix.urlShortenerPgs.models;

import javax.persistence.*;

@Entity
@Table(name = "short_url") //new
public final class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "short_url") //new
    private Long shortUrl;

    @Column(name = "original_url") //new
    private String originalUrl;

    public ShortUrl(){

    }

    public ShortUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Long getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(Long shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    @Override
    public String toString() {
        return "ShortUrl{" +
                "shortUrl=" + shortUrl +
                ", originalUrl='" + originalUrl + '\'' +
                '}';
    }
}
