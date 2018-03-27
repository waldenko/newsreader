package com.dwalczak.newsreader.service.mapper;

import com.dwalczak.newsreader.service.dto.Article;
import com.dwalczak.newsreader.service.dto.ArticleList;
import com.dwalczak.newsreader.newsapi.dto.NewsApiArticle;
import com.dwalczak.newsreader.newsapi.dto.NewsApiArticlesResult;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Transformacja DTO między warstwą integracyjną newsapi.org a warstwą usługową
 */
@ParametersAreNonnullByDefault
public class ArticleMapper {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Nonnull
    public static ArticleList map(NewsApiArticlesResult articles) {
        return new ArticleList()
                .setTotalCount(requireNonNull(articles.getTotalResults()))
                .setArticles(articles.getArticles() != null
                        ? articles.getArticles().stream().map(ArticleMapper::map).collect(Collectors.toList())
                        : Collections.emptyList())
                ;
    }

    @Nonnull
    private static Article map(NewsApiArticle article) {
        return new Article()
                .setArticleUrl(requireNonNull(article.getUrl(), "getUrl"))
                .setAuthor(article.getAuthor())
                .setDate(map2Date(requireNonNull(article.getPublishedAt(), "getPublishedAt")))
                .setDescription(article.getDescription())
                .setImageUrl(article.getUrlToImage())
                .setSourceName(requireNonNull(requireNonNull(article.getSource(), "getSource").getName(), "getSourceName"))
                .setTitle(requireNonNull(article.getTitle(), "getTitle"))
                ;
    }

    @Nonnull
    private static Date map2Date(String date) {
        try {
            return SDF.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Can't parse date: " + date, e);
        }
    }
}
