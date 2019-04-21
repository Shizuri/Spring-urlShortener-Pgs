package com.sorsix.urlShortenerPgs.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "short_url")
public final class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "short_url")
    private Long shortUrl;

    @Column(name = "original_url", columnDefinition = "text")
    @NotNull
    private String originalUrl;

    @Column(name = "number_of_visits")
    private Long numberOfVisits = 0L;

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

    public Long getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(Long numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    @Override
    public String toString() {
        return "ShortUrl{" +
                "shortUrl=" + shortUrl +
                ", originalUrl='" + originalUrl + '\'' +
                ", numberOfVisits=" + numberOfVisits +
                '}';
    }
}
