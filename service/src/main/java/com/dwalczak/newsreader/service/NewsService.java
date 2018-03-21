package com.dwalczak.newsreader.service;

import com.dwalczak.newsreader.model.ArticleFilter;
import com.dwalczak.newsreader.model.ArticleList;
import com.dwalczak.newsreader.newsapi.NewsapiClient;
import com.dwalczak.newsreader.newsapi.NewsapiException;
import com.dwalczak.newsreader.newsapi.dto.NewsApiArticlesResult;
import com.dwalczak.newsreader.service.mapper.ArticleFilterMapper;
import com.dwalczak.newsreader.service.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@Slf4j
@Service
@ParametersAreNonnullByDefault
public class NewsService {

    @Autowired private NewsapiClient newsapiClient;

    @Nonnull
    public ArticleList findArticles(ArticleFilter filter) {
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
