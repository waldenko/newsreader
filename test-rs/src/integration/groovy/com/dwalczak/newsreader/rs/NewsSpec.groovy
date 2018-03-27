package com.dwalczak.newsreader.rs

import spock.lang.Specification
import spock.lang.Unroll

import static com.dwalczak.newsreader.rs.NewsClient.getNews

class NewsSpec extends Specification {

    def "check fields"() {
        when:
        def resp = getNews()

        then:
        resp.status == 200
        resp.data.country == 'pl'
        resp.data.category == 'technology'
        resp.data.totalCount != null
        resp.data.articles?.size > 0
        resp.data.articles[0].title != null
        resp.data.articles[0].date != null
        resp.data.articles[0].date ==~ /\d{4}-\d{2}-\d{2}/
        resp.data.articles[0].sourceName != null
        resp.data.articles[0].articleUrl != null
    }

    @Unroll
    def "check page filter #pageNumber, #pageSize"() {
        when:
        def resp = getNews(pageNumber: pageNumber, pageSize: pageSize)

        then:
        resp.status == 200
        resp.data.articles?.size == pageSize

        where:
        pageNumber | pageSize
        1          | 5
        2          | 5
        1          | 10
    }

    @Unroll
    def "check searchPhrase filter #searchPhrase"() {
        when:
        def resp = getNews(searchPhrase: searchPhrase)

        then:
        resp.status == 200
        resp.data.articles?.size == 0 || resp.data.articles.every { it.title.toLowerCase().contains(searchPhrase) || it.description.toLowerCase().contains(searchPhrase) }

        where:
        searchPhrase << ['java', 'amd', 'intel']
    }

    def "check page filter - last page"() {
        when:
        def r1 = getNews()

        then:
        r1.status == 200
        r1.data.totalCount > 0

        when:
        int pageSize = 3
        int lastPageSize = r1.data.totalCount % pageSize
        int lastPageNumber = (int) (r1.data.totalCount / pageSize) + (lastPageSize == 0 ? 0 : 1)
        def r2 = getNews(pageSize: pageSize, pageNumber: lastPageNumber)
        then:
        r2.status == 200
        r2.data.totalCount == r1.data.totalCount
        r2.data.articles.size == (lastPageSize == 0 ? pageSize : lastPageSize)
    }
}
