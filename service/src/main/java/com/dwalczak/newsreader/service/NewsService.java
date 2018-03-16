package com.dwalczak.newsreader.service;

import com.dwalczak.newsreader.model.Article;
import com.dwalczak.newsreader.model.ArticleFilter;
import com.dwalczak.newsreader.model.ArticleList;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class NewsService {

    public ArticleList findArticles(ArticleFilter filter) {
        return new ArticleList()
                .setTotaCount(1)
                .setArticles(Collections.singletonList(new Article()
                        .setArticleUrl("url")
                        .setAuthor("Autor")
                        .setDate(new Date())
                        .setDescription("Opis")
                        .setSourceName("Wydawca")
                        .setTitle("Artykuł")
                        .setImageUrl("Obrazek")
                ));
    }
}
