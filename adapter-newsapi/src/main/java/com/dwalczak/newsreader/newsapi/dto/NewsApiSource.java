package com.dwalczak.newsreader.newsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

@Data @Accessors(chain = true) @NoArgsConstructor
public class NewsApiSource {
    @Nullable private String id;
    @Nullable private String name;
    @Nullable private String url;
    @Nullable private String description;
    @Nullable private String category;
    @Nullable private String language;
    @Nullable private String country;

}
