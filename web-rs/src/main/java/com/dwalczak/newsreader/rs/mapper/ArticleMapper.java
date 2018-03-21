package com.dwalczak.newsreader.rs.mapper;

import com.dwalczak.newsreader.rs.model.Article;
import com.dwalczak.newsreader.rs.model.ArticleList;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

/**
 * Transformacja DTO między warstwą usług REST a warstwą usługową
 */
@ParametersAreNonnullByDefault
public class ArticleMapper {

    @Nonnull
    public static ArticleList map(String country, String category, com.dwalczak.newsreader.model.ArticleList list) {
        return new ArticleList()
                .category(category)
                .country(country)
                .articles(list.getTotaCount() > 0 ? list.getArticles().stream().map(ArticleMapper::map).collect(Collectors.toList()) : null)
                .totaCount(list.getTotaCount());
    }

    @Nonnull
    private static Article map(com.dwalczak.newsreader.model.Article article) {
        return new Article()
                .articleUrl(article.getArticleUrl())
                .author(article.getAuthor())
                .date(Instant.ofEpochMilli(article.getDate().getTime()).atZone(ZoneOffset.UTC).toLocalDate())
                .description(article.getDescription())
                .sourceName(article.getSourceName())
                .title(article.getTitle())
                .imageUrl(article.getImageUrl());
    }
}
