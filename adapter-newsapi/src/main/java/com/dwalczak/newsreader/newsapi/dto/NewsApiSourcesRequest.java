package com.dwalczak.newsreader.newsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

@Data @Accessors(chain = true) @NoArgsConstructor
public class NewsApiSourcesRequest {
    @Nullable private String country;
    @Nullable private String category;
    @Nullable private String language;
}
