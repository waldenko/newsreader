package com.dwalczak.newsreader.newsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Data @Accessors(chain = true) @NoArgsConstructor
public class NewsApiArticlesResult {
    @Nonnull private String status;
    @Nullable private NewsApiError error;
    @Nullable private Integer totalResults;
    @Nullable private List<NewsApiArticle> articles;
}
