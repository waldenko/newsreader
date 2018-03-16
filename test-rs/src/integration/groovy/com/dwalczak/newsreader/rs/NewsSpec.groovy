package com.dwalczak.newsreader.rs

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class NewsSpec extends Specification {

    @Shared def client = new RESTClient('http://127.0.0.1:8080/')

    def "check fields"() {
        when:
        def resp = getNews('pl', 'technology')

        then:
        resp.status == 200
        resp.data.country == 'Polska'
        resp.data.category == 'technology'
        resp.data.articles?.size > 0
        resp.data.articles[0].author != null
        resp.data.articles[0].title != null
        resp.data.articles[0].description != null
        resp.data.articles[0].date != null
        resp.data.articles[0].date ==~ /\d{4}-\d{2}-\d{2}/
        resp.data.articles[0].sourceName != null
        resp.data.articles[0].articleUrl != null
    }

    def getNews(def lang, def category) {
        doGet("news/$lang/$category")
    }

    def doGet(def path) {
        println "HTTP REQUEST: ${client.uri}$path"
        def resp = client.get(path: path)
        println "HTTP RESULT $resp.status [$resp.allHeaders] $resp.data}"
        resp
    }
}
