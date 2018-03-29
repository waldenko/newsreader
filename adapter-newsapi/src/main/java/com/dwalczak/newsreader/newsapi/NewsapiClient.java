package com.dwalczak.newsreader.newsapi;

import com.dwalczak.newsreader.newsapi.dto.NewsApiArticlesResult;
import com.dwalczak.newsreader.newsapi.dto.NewsApiSourcesRequest;
import com.dwalczak.newsreader.newsapi.dto.NewsApiSourcesResult;
import com.dwalczak.newsreader.newsapi.dto.NewsApiTopHeadlinesRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;

@Slf4j
@Component
@ParametersAreNonnullByDefault
public class NewsapiClient {

    private static final String ENDPOINT_TOP_HEADLINES  = "https://newsapi.org/v2/top-headlines";
    private static final String ENDPOINT_SOURCES        = "https://newsapi.org/v2/sources";

    @Value("${newsapi_org_api_key}")
    private String apiKey;


    public NewsApiArticlesResult getTopHeadlines(NewsApiTopHeadlinesRequest request) {
        return doGet(RequestMapper.map(ENDPOINT_TOP_HEADLINES, apiKey, request), NewsApiArticlesResult.class);
    }

    public NewsApiSourcesResult getSources(NewsApiSourcesRequest request) {
        return doGet(RequestMapper.map(ENDPOINT_SOURCES, apiKey, request), NewsApiSourcesResult.class);
    }

    private <R> R doGet(String url, Class<R> clazz) {
        try {
            RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
            restTemplate.setInterceptors(Collections.singletonList(new LoggingRequestInterceptor()));
            return restTemplate.getForObject(url, clazz);
        } catch (Exception e) {
            log.error("Newsapi request error", e);
            throw new NewsapiException(e);
        }

    }
}
