package com.dwalczak.newsreader.service.validator

import com.dwalczak.newsreader.service.NewsService
import com.dwalczak.newsreader.service.dto.ArticleSourceFilter
import spock.lang.Specification
import spock.lang.Unroll

class ArticleSourceFilterValidatorSpec extends Specification {

    @Unroll
    def "check filter validation: #filter"() {
        given:
        def ctx = new ConstraintValidatorTestContext()
        def validator = new ArticleSourceFilterValidator(stubNewsService())

        when:
        validator.isValid(prepareFilter(filter), ctx)

        then:
        ctx.defaultConstraintViolationDisabled
        ctx.failedValidations == failedValidations

        where:
        filter          | failedValidations
        [country: 'X']  | [['country', 'Country must be equal \'pl\'']]
        [category: 'X'] | [['category', 'Category must be one of value: [c1, c2]']]
    }

    def prepareFilter(Map map) {
        new ArticleSourceFilter(
                category: map.getOrDefault('category', 'c1'),
                country: map.getOrDefault('country', 'pl')
        )
    }

    def stubNewsService() {
        Stub(NewsService) {
            getCategories() >> ['c1', 'c2']
        }
    }
}
