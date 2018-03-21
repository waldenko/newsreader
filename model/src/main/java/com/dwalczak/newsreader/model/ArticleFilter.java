package com.dwalczak.newsreader.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Data
@Accessors(chain = true) @NoArgsConstructor
public class ArticleFilter {
    @Nonnull private String country;
    @Nonnull private String category;
    @Nonnull private PaginationFilter paginationFilter;
    @Nullable private String searchPhrase;
}
