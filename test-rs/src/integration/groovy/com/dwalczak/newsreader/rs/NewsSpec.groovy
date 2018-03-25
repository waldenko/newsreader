package com.dwalczak.newsreader.rs

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class NewsSpec extends Specification {

    @Shared def client = getRestClient('http://127.0.0.1:8080/')

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
    def "check base filter #country, #category"() {
        when:
        def resp = getNews(country: country, category: category)

        then:
        resp.status == 200
        resp.data.country == country
        resp.data.category == category
        resp.data.articles?.size > 0

        where:
        country | category
        'pl'    | 'business'
        'pl'    | 'technology'
        'de'    | 'technology'
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

    def getNews(Map params = [:]) {
        def country = params.country ?: 'pl'
        def category = params.category ?: 'technology'
        params.remove('country')
        params.remove('category')
        doGet("news/$country/$category", params)
    }

    def doGet(def path, Map query) {
        println "HTTP REQUEST: ${client.uri}$path"
        def resp = client.get(path: path, query: query)
        println "HTTP RESULT $resp.status [$resp.allHeaders] $resp.data"
        resp
    }

    def getRestClient(def uri) {
        def client = new RESTClient(uri)
        client.handler.failure = client.handler.success
        client
    }
}
