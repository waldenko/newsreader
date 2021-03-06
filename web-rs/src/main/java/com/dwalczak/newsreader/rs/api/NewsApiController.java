package com.dwalczak.newsreader.rs.api;

import com.dwalczak.newsreader.rs.dto.ArticleSource;
import com.dwalczak.newsreader.rs.mapper.ArticleFilterMapper;
import com.dwalczak.newsreader.rs.mapper.ArticleMapper;
import com.dwalczak.newsreader.rs.dto.ArticleList;
import com.dwalczak.newsreader.rs.mapper.ArticleSourceFilterMapper;
import com.dwalczak.newsreader.rs.mapper.ArticleSourceMapper;
import com.dwalczak.newsreader.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<ArticleList> listArticle(
            @ApiParam(value = "Kod kraju",required=true) @PathVariable("country") String country,
            @ApiParam(value = "Kod kategorii (business, entertainment, general, health, science, sports, technology)",required=true) @PathVariable("category") String category,
            @ApiParam(value = "Nr strony (domyślna wartość 1)") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @ApiParam(value = "Rozmiar strony (domyślna wartość 10)") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @ApiParam(value = "Szukany tekst w artykułach") @Valid @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        com.dwalczak.newsreader.service.dto.ArticleList articles = newsService.findArticles(
                ArticleFilterMapper.map(country, category, pageNumber, pageSize, searchPhrase));
        return ResponseEntity.ok(ArticleMapper.map(country, category, articles));
    }

    @Override public ResponseEntity<List<String>> listCategory() {
        return ResponseEntity.ok(newsService.getCategories());
    }

    @Override public ResponseEntity<List<ArticleSource>> listSource(
            @ApiParam(value = "Kod kraju",required=true) @PathVariable("country") String country,
            @ApiParam(value = "Kod kategorii (business, entertainment, general, health, science, sports, technology)",required=true) @PathVariable("category") String category) {
        return ResponseEntity.ok(ArticleSourceMapper.map(newsService.getSources(ArticleSourceFilterMapper.map(country, category))));
    }
}
