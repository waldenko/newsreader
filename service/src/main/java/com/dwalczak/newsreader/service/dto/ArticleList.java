package com.dwalczak.newsreader.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;
import java.util.List;

@Data
@Accessors(chain = true) @NoArgsConstructor
public class ArticleList {
    @Nonnull private Integer totalCount;
    @Nonnull private List<Article> articles;
}
