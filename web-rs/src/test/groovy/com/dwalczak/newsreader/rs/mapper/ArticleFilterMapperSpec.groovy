package com.dwalczak.newsreader.rs.mapper

import com.dwalczak.newsreader.model.ArticleFilter
import com.dwalczak.newsreader.model.PaginationFilter
import spock.lang.Specification
import spock.lang.Unroll

class ArticleFilterMapperSpec extends Specification {

    @Unroll
    def "check filter mapping: #srcDto"() {
        given:
        dstDto.country = srcDto.country = 'x1'
        dstDto.category = srcDto.category = 'x2'

        when:
        def result = ArticleFilterMapper.map(srcDto.country, srcDto.category, srcDto.pageNumber, srcDto.pageSize, srcDto.searchPhrase)

        then:
        result == dstDto

        where:
        srcDto               | dstDto
        [:]                  | new ArticleFilter(paginationFilter: new PaginationFilter(pageSize: 10, pageNumber: 1))
        [searchPhrase: 'x3'] | new ArticleFilter(searchPhrase: 'x3', paginationFilter: new PaginationFilter(pageSize: 10, pageNumber: 1))
        [pageNumber: 3]      | new ArticleFilter(paginationFilter: new PaginationFilter(pageSize: 10, pageNumber: 3))
        [pageSize: 30]       | new ArticleFilter(paginationFilter: new PaginationFilter(pageSize: 30, pageNumber: 1))
    }

}
