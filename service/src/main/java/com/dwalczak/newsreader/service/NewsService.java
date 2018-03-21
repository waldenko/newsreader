package com.dwalczak.newsreader.service;

import com.dwalczak.newsreader.model.ArticleFilter;
import com.dwalczak.newsreader.model.ArticleList;
import com.dwalczak.newsreader.newsapi.NewsapiClient;
import com.dwalczak.newsreader.newsapi.NewsapiException;
import com.dwalczak.newsreader.newsapi.dto.NewsApiArticlesResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NewsService {

    @Autowired private NewsapiClient newsapiClient;

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
