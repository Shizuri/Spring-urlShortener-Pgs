package com.sorsix.urlShortenerPgs.models;

import org.hibernate.validator.constraints.Range;

public final class NewShort {

    @Range(min = 0, max = 999999999)
    public String newShortUrl;
}
