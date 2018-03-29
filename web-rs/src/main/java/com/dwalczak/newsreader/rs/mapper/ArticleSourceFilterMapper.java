package com.dwalczak.newsreader.rs.mapper;

import com.dwalczak.newsreader.service.dto.ArticleFilter;
import com.dwalczak.newsreader.service.dto.ArticleSourceFilter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Transformacja DTO między warstwą usług REST a warstwą usługową
 */
@ParametersAreNonnullByDefault
public class ArticleSourceFilterMapper {

    @Nonnull
    public static ArticleSourceFilter map(String country, String category) {
        return new ArticleSourceFilter()
                .setCategory(category)
                .setCountry(country)
                ;
    }
}
