package com.dwalczak.newsreader.newsapi;

import com.dwalczak.newsreader.newsapi.dto.NewsApiArticlesResult;
import com.dwalczak.newsreader.newsapi.dto.NewsApiTopHeadlinesRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.ParametersAreNonnullByDefault;

@Component
@ParametersAreNonnullByDefault
public class NewsapiClient {

    private static final String BASE_URL = "https://newsapi.org/v2/top-headlines";

    @Value("${newsapi_org_api_key}")
    private String apiKey;


    public NewsApiArticlesResult getTopHeadlines(NewsApiTopHeadlinesRequest request) {
        return new RestTemplate().getForObject(RequestMapper.map(BASE_URL, apiKey, request), NewsApiArticlesResult.class);
    }
}
