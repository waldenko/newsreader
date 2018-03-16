package com.dwalczak.newsreader.rs.mapper;

import com.dwalczak.newsreader.rs.model.Article;

import java.time.Instant;
import java.time.ZoneOffset;

public class ArticleMapper {

    static public Article map(com.dwalczak.newsreader.model.Article article) {
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
