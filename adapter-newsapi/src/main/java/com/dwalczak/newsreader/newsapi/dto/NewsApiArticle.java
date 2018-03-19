package com.dwalczak.newsreader.newsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

@Data @Accessors(chain = true) @NoArgsConstructor
public class NewsApiArticle {
    @Nullable private NewsApiSource source;
    @Nullable private String author;
    @Nullable private String title;
    @Nullable private String description;
    @Nullable private String url;
    @Nullable private String urlToImage;
    @Nullable private String publishedAt;

}
