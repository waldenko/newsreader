package com.dwalczak.newsreader.service;

import com.dwalczak.newsreader.service.dto.ArticleFilter;
import com.dwalczak.newsreader.service.dto.ArticleList;
import com.dwalczak.newsreader.newsapi.NewsapiClient;
import com.dwalczak.newsreader.newsapi.NewsapiException;
import com.dwalczak.newsreader.newsapi.dto.NewsApiArticlesResult;
import com.dwalczak.newsreader.service.mapper.ArticleFilterMapper;
import com.dwalczak.newsreader.service.mapper.ArticleMapper;
import com.dwalczak.newsreader.service.validator.IsCorrectArticleFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@Slf4j
@Service
@ParametersAreNonnullByDefault
@Validated
public class NewsService {

    @Autowired private NewsapiClient newsapiClient;

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
}
