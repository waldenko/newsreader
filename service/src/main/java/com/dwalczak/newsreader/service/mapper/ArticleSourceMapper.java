package com.dwalczak.newsreader.service.mapper;

import com.dwalczak.newsreader.newsapi.dto.NewsApiSource;
import com.dwalczak.newsreader.newsapi.dto.NewsApiSourcesResult;
import com.dwalczak.newsreader.service.dto.ArticleSource;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Transformacja DTO między warstwą integracyjną newsapi.org a warstwą usługową
 */
@ParametersAreNonnullByDefault
public class ArticleSourceMapper {

    @Nonnull
    public static List<ArticleSource> map(NewsApiSourcesResult result) {
        return result.getSources().stream().map(ArticleSourceMapper::map).collect(Collectors.toList());
    }

    private static ArticleSource map(NewsApiSource source) {
        return new ArticleSource()
                .setId(Objects.requireNonNull(source.getId(), "getId"))
                .setName(Objects.requireNonNull(source.getName(), "getName"));
    }
}
