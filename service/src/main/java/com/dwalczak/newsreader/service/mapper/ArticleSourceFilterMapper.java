package com.dwalczak.newsreader.service.mapper;

import com.dwalczak.newsreader.newsapi.dto.NewsApiSourcesRequest;
import com.dwalczak.newsreader.service.dto.ArticleSourceFilter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Transformacja DTO między warstwą integracyjną newsapi.org a warstwą usługową
 */
@ParametersAreNonnullByDefault
public class ArticleSourceFilterMapper {

    @Nonnull
    public static NewsApiSourcesRequest map(ArticleSourceFilter filter) {
        return new NewsApiSourcesRequest()
                .setCategory(filter.getCategory())
                .setCountry(filter.getCountry())
                ;

    }
}
