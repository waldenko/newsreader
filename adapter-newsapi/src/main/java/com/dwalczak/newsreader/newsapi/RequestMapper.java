package com.dwalczak.newsreader.newsapi;

import com.dwalczak.newsreader.newsapi.dto.NewsApiTopHeadlinesRequest;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
public class RequestMapper {

    public static String map(String baseUrl, String apiKey, NewsApiTopHeadlinesRequest request) {
        StringBuilder buf = new StringBuilder();
        addParam(buf, "apiKey", apiKey);
        addParam(buf, "category", request.getCategory());
        addParam(buf, "country", request.getCountry());
        addParam(buf, "sources", fromList(request.getSources()));
        addParam(buf, "q", request.getQ());
        addParam(buf, "page", fromInteger(request.getPage()));
        addParam(buf, "pageSize", fromInteger(request.getPageSize()));
        return baseUrl + buf.toString();
    }

    private static void addParam(StringBuilder buf, String paramName, @Nullable String paramValue) {
        if (paramValue != null) {
            buf.append(buf.length() == 0 ? '?' : '&').append(paramName).append('=').append(paramValue);
        }
    }

    private static String fromInteger(@Nullable Integer number) {
        return number != null ? number.toString() : null;
    }

    private static String fromList(@Nullable List<String> list) {
        return list != null ? list.stream().map(Object::toString).collect(Collectors.joining (",")) : null;
    }
}
