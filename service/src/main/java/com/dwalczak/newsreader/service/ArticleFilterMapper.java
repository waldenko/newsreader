package com.dwalczak.newsreader.service;

import com.dwalczak.newsreader.model.ArticleFilter;
import com.dwalczak.newsreader.newsapi.dto.NewsApiTopHeadlinesRequest;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ArticleFilterMapper {

    public static NewsApiTopHeadlinesRequest map(ArticleFilter filter) {
        return new NewsApiTopHeadlinesRequest()
                .setCategory(filter.getCategory())
                .setCountry(filter.getCountry())
                .setPage(filter.getPaginationFilter().getPageNumber())
                .setPageSize(filter.getPaginationFilter().getPageSize())
                ;
    }
}
