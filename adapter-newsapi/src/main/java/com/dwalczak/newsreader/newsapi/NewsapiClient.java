package com.dwalczak.newsreader.newsapi;

import com.dwalczak.newsreader.newsapi.dto.NewsApiArticlesResult;
import com.dwalczak.newsreader.newsapi.dto.NewsApiTopHeadlinesRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;

@Component
@ParametersAreNonnullByDefault
public class NewsapiClient {

    private static final String BASE_URL = "https://newsapi.org/v2/top-headlines";

    @Value("${newsapi_org_api_key}")
    private String apiKey;


    public NewsApiArticlesResult getTopHeadlines(NewsApiTopHeadlinesRequest request) {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        restTemplate.setInterceptors(Collections.singletonList(new LoggingRequestInterceptor()));
        return restTemplate.getForObject(RequestMapper.map(BASE_URL, apiKey, request), NewsApiArticlesResult.class);
    }
}
