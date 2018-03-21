package com.dwalczak.newsreader.rs.mapper;

import com.dwalczak.newsreader.model.ArticleFilter;
import com.dwalczak.newsreader.model.PaginationFilter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

/**
 * Transformacja DTO między warstwą usług REST a warstwą usługową
 */
@ParametersAreNonnullByDefault
public class ArticleFilterMapper {

    @Nonnull
    public static ArticleFilter map(String country, String category, @Nullable Integer pageNumber, @Nullable Integer pageSize, @Nullable String searchPhrase) {
        return new ArticleFilter()
                .setCategory(category)
                .setCountry(country)
                .setSearchPhrase(searchPhrase)
                .setPaginationFilter(new PaginationFilter()
                        .setPageNumber(Optional.ofNullable(pageNumber).orElse(1))
                        .setPageSize(Optional.ofNullable(pageSize).orElse(10)));
    }
}
