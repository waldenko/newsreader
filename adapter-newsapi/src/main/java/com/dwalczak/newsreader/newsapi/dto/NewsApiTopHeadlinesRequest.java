package com.dwalczak.newsreader.newsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;
import java.util.List;

@Data @Accessors(chain = true) @NoArgsConstructor
public class NewsApiTopHeadlinesRequest {
    @Nullable private String country;
    @Nullable private String category;
    @Nullable private List<String> sources;
    @Nullable private String q;
    @Nullable private Integer pageSize;
    @Nullable private Integer page;
}
