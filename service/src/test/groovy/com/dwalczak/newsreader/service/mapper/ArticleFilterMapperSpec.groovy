package com.dwalczak.newsreader.service.mapper

import com.dwalczak.newsreader.model.ArticleFilter
import com.dwalczak.newsreader.model.PaginationFilter
import com.dwalczak.newsreader.newsapi.dto.NewsApiTopHeadlinesRequest
import spock.lang.Specification

class ArticleFilterMapperSpec extends Specification {

    def "check filter mapping: #srcDto"() {
        given:
        ArticleFilter srcDto = new ArticleFilter(
                country: 'x1',
                category: 'x2',
                searchPhrase: 'x3',
                paginationFilter: new PaginationFilter(pageSize: 10, pageNumber: 2)
        )
        NewsApiTopHeadlinesRequest dstDto = new NewsApiTopHeadlinesRequest(
                country: 'x1',
                category: 'x2',
                q: 'x3',
                pageSize: 10,
                page: 2
        )

        when:
        def result = ArticleFilterMapper.map(srcDto)

        then:
        result == dstDto
    }

}
