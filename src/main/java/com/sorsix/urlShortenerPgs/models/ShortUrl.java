package com.sorsix.urlShortenerPgs.models;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "short_url_table")
public final class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "short_url")
    @NotNull
    private Long shortUrl;

    @Column(name = "original_url", columnDefinition = "text")
    @NotNull
    @URL
    private String originalUrl;

    @Column(name = "number_of_visits")
    private Long numberOfVisits = 0L;

    public ShortUrl() {
    }

    public ShortUrl(@NotNull Long shortUrl, @NotNull @URL String originalUrl) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", shortUrl=" + shortUrl +
                ", originalUrl='" + originalUrl + '\'' +
                ", numberOfVisits=" + numberOfVisits +
                '}';
    }
}
