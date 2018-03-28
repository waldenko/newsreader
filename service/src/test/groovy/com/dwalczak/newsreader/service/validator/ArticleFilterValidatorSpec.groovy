package com.dwalczak.newsreader.service.validator

import com.dwalczak.newsreader.service.NewsService
import com.dwalczak.newsreader.service.dto.ArticleFilter
import com.dwalczak.newsreader.service.dto.PaginationFilter
import spock.lang.Specification
import spock.lang.Unroll

class ArticleFilterValidatorSpec extends Specification {

    @Unroll
    def "check filter validation: #filter"() {
        given:
        def ctx = new ConstraintValidatorTestContext()
        def validator = new ArticleFilterValidator(stubNewsService())

        when:
        validator.isValid(prepareFilter(filter), ctx)

        then:
        ctx.defaultConstraintViolationDisabled
        ctx.failedValidations == failedValidations

        where:
        filter          | failedValidations
        [country: 'X']  | [['country', 'Country must be equal \'pl\'']]
        [category: 'X'] | [['category', 'Category must be one of value: [c1, c2]']]
        [pageSize: 0]   | [['pageSize', 'PageSize must be greater than 0']]
        [pageNumber: 0] | [['pageNumber', 'PageNumber must be greater than 0']]
    }

    def prepareFilter(Map map) {
        new ArticleFilter(
                category: map.getOrDefault('category', 'c1'),
                country: map.getOrDefault('country', 'pl'),
                paginationFilter: new PaginationFilter(
                        pageSize: map.getOrDefault('pageSize', 10),
                        pageNumber: map.getOrDefault('pageNumber', 1),
                )
        )
    }

    def stubNewsService() {
        Stub(NewsService) {
            getCategories() >> ['c1', 'c2']
        }
    }
}
