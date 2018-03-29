package com.dwalczak.newsreader.newsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

@Data @Accessors(chain = true) @NoArgsConstructor
public class NewsApiSourceShort {
    @Nullable private String id;
    @Nullable private String name;
}
