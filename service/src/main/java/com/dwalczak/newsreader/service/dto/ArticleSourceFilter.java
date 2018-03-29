package com.dwalczak.newsreader.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;

@Data @Accessors(chain = true) @NoArgsConstructor
public class ArticleSourceFilter {
    @Nonnull private String country;
    @Nonnull private String category;
}
