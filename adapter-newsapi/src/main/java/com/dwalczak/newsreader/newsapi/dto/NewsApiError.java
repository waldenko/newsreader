package com.dwalczak.newsreader.newsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;

@Data @Accessors(chain = true) @NoArgsConstructor
public class NewsApiError {
    @Nonnull private String code;
    @Nonnull private String message;
}
