package com.dwalczak.newsreader.rs

import groovyx.net.http.RESTClient

class NewsClient {
    def static client = getRestClient('http://127.0.0.1:8080/')


    def static getNews(Map params = [:]) {
        def country = params.country ?: 'pl'
        def category = params.category ?: 'technology'
        params.remove('country')
        params.remove('category')
        doGet("news/$country/$category", params)
    }

    def static getCategories() {
        doGet("categories")
    }

    def static getSources(Map params = [:]) {
        def country = params.country ?: 'pl'
        def category = params.category ?: 'technology'
        doGet("sources/$country/$category")
    }

    def static doGet(def path, Map query = [:]) {
        println "HTTP REQUEST: ${client.uri}$path"
        def resp = client.get(path: path, query: query)
        println "HTTP RESULT $resp.status [$resp.allHeaders] $resp.data"
        resp
    }

    def static getRestClient(def uri) {
        def client = new RESTClient(uri)
        client.handler.failure = client.handler.success
        client
    }
}
