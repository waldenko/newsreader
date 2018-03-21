package com.dwalczak.newsreader.service.mapper;

import com.dwalczak.newsreader.model.ArticleFilter;
import com.dwalczak.newsreader.newsapi.dto.NewsApiTopHeadlinesRequest;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Transformacja DTO między warstwą integracyjną newsapi.org a warstwą usługową
 */
@ParametersAreNonnullByDefault
public class ArticleFilterMapper {

    @Nonnull
    public static NewsApiTopHeadlinesRequest map(ArticleFilter filter) {
        return new NewsApiTopHeadlinesRequest()
                .setCategory(filter.getCategory())
                .setCountry(filter.getCountry())
                .setQ(filter.getSearchPhrase())
                .setPage(filter.getPaginationFilter().getPageNumber())
                .setPageSize(filter.getPaginationFilter().getPageSize())
                ;
    }
}
