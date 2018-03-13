package com.dwalczak.newsreader.rs.api;

import com.dwalczak.newsreader.rs.api.NewsApi;
import com.dwalczak.newsreader.rs.model.Article;
import com.dwalczak.newsreader.rs.model.ArticleList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@Controller
public class NewsApiController implements NewsApi {

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
    public ResponseEntity<ArticleList> listArticle(String lang, String category) {
        ArticleList result = new ArticleList();
        result.setCategory(category);
        result.setCountry("Polska");
        result.setArticles(Collections.singletonList(new Article()
                .articleUrl("url")
                .author("Autor")
                .date(LocalDate.now().minusDays(2))
                .description("Opis")
                .sourceName("Wydawca")
                .title("Artykuł")
                .imageUrl("Obrazek")
        ));
        return ResponseEntity.ok(result);
    }
}
