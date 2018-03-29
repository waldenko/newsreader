package com.dwalczak.newsreader.rs.mapper;

import com.dwalczak.newsreader.rs.dto.ArticleSource;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transformacja DTO między warstwą usług REST a warstwą usługową
 */
@ParametersAreNonnullByDefault
public class ArticleSourceMapper {

    public static List<ArticleSource> map(List<com.dwalczak.newsreader.service.dto.ArticleSource> sources) {
        return sources.stream().map(s -> new ArticleSource().id(s.getId()).name(s.getName())).collect(Collectors.toList());
    }
}
