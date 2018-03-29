package com.dwalczak.newsreader.service;

import com.dwalczak.newsreader.newsapi.NewsapiClient;
import com.dwalczak.newsreader.newsapi.NewsapiException;
import com.dwalczak.newsreader.newsapi.dto.NewsApiArticlesResult;
import com.dwalczak.newsreader.newsapi.dto.NewsApiSourcesResult;
import com.dwalczak.newsreader.service.dto.ArticleFilter;
import com.dwalczak.newsreader.service.dto.ArticleList;
import com.dwalczak.newsreader.service.dto.ArticleSource;
import com.dwalczak.newsreader.service.dto.ArticleSourceFilter;
import com.dwalczak.newsreader.service.mapper.ArticleFilterMapper;
import com.dwalczak.newsreader.service.mapper.ArticleMapper;
import com.dwalczak.newsreader.service.mapper.ArticleSourceFilterMapper;
import com.dwalczak.newsreader.service.mapper.ArticleSourceMapper;
import com.dwalczak.newsreader.service.validator.IsCorrectArticleFilter;
import com.dwalczak.newsreader.service.validator.IsCorrectArticleSourceFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@ParametersAreNonnullByDefault
@Validated
public class NewsService {

    private final NewsapiClient newsapiClient;

    private static List<String> CATEGORIES = Arrays.asList("business", "entertainment", "general", "health", "science", "sports", "technology");

    @Autowired public NewsService(@Nonnull NewsapiClient newsapiClient) {
        this.newsapiClient = newsapiClient;
    }

    @Nonnull
    public ArticleList findArticles(@IsCorrectArticleFilter ArticleFilter filter) {
        NewsApiArticlesResult res = newsapiClient.getTopHeadlines(ArticleFilterMapper.map(filter));
        try {
            return ArticleMapper.map(res);
        } catch (NewsapiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Can't prepare article list", e);
            throw new NewsServiceException(e);
        }
    }

    @Nonnull
    public List<String> getCategories() {
        return CATEGORIES;
    }

    @Cacheable("sources")
    @Nonnull
    public List<ArticleSource> getSources(@IsCorrectArticleSourceFilter ArticleSourceFilter filter) {
        NewsApiSourcesResult res = newsapiClient.getSources(ArticleSourceFilterMapper.map(filter));
        try {
            return ArticleSourceMapper.map(res);
        } catch (Exception e) {
            log.error("Can't prepare source list", e);
            throw new NewsServiceException(e);
        }

    }
}
