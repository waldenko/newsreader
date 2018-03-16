package com.dwalczak.newsreader.rs.api;

import com.dwalczak.newsreader.rs.mapper.ArticleFilterMapper;
import com.dwalczak.newsreader.rs.mapper.ArticleMapper;
import com.dwalczak.newsreader.rs.model.ArticleList;
import com.dwalczak.newsreader.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class NewsApiController implements NewsApi {

    @Autowired private NewsService newsService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public NewsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ArticleList> listArticle(@ApiParam(value = "Kod języka",required=true) @PathVariable("lang") String lang, @ApiParam(value = "Kod kategorii",required=true) @PathVariable("category") String category) {
        com.dwalczak.newsreader.model.ArticleList articles = newsService.findArticles(ArticleFilterMapper.map(lang, category));
        ArticleList result = new ArticleList();
        result.setCategory(category);
        result.setCountry("Polska");
        result.setArticles(articles.getTotaCount() > 0 ? articles.getArticles().stream().map(ArticleMapper::map).collect(Collectors.toList()) : null);
        return ResponseEntity.ok(result);
    }
}
