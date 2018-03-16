package com.dwalczak.newsreader.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;

@Data @Accessors(chain = true) @NoArgsConstructor
public class Article {
    @Nonnull private String author;
    @Nonnull private String title;
    @Nonnull private String description;
    @Nonnull private Date date;
    @Nonnull private String sourceName;
    @Nonnull private String articleUrl;
    @Nullable private String imageUrl;
}
