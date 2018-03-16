package com.dwalczak.newsreader.rs.mapper;

import com.dwalczak.newsreader.model.ArticleFilter;

public class ArticleFilterMapper {

    static public ArticleFilter map(String country, String category) {
        return new ArticleFilter().setCategory(category).setCountry(country);
    }
}
