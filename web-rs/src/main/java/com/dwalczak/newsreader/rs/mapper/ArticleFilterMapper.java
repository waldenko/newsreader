package com.dwalczak.newsreader.rs.mapper;

import com.dwalczak.newsreader.model.ArticleFilter;
import com.dwalczak.newsreader.model.PaginationFilter;

public class ArticleFilterMapper {

    static public ArticleFilter map(String country, String category) {
        return new ArticleFilter()
                .setCategory(category)
                .setCountry(country)
                .setPaginationFilter(new PaginationFilter()
                        .setPageNumber(1)
                        .setPageSize(10));
    }
}
