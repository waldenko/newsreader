package com.dwalczak.newsreader.rs

import spock.lang.Specification
import spock.lang.Unroll

import static com.dwalczak.newsreader.rs.NewsClient.*

class NewsInvalidRequestSpec extends Specification {


    @Unroll
    def "check invalid path - #path"() {
        when:
        def resp = doGet(path)

        then:
        resp.status == 404

        where:
        path << ['/news', '/news/x']
    }

    @Unroll
    def "check invalid param value - #param"() {
        when:
        def resp = getNews(param)

        then:
        resp.status == 200
        resp.data.errorCode == 'validation_failed'
        resp.data.message == 'Błąd walidacji'
        resp.data.fields[0].field == field
        resp.data.fields[0].message == message

        where:

        param            | field        | message
        [country: 'X']   | 'country'    | 'Country must be equal \'pl\''
        [category: 'X']  | 'category'   | 'Category must be one of value: [business, entertainment, general, health, science, sports, technology]'
        [pageNumber: -1] | 'pageNumber' | 'PageNumber must be greater than 0'
        [pageSize: -1]   | 'pageSize'   | 'PageSize must be greater than 0'
    }



}
