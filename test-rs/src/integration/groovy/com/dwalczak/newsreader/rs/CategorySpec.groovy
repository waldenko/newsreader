package com.dwalczak.newsreader.rs

import spock.lang.Specification

import static com.dwalczak.newsreader.rs.NewsClient.getCategories

class CategorySpec extends Specification {

    def "check getCategories"() {
        when:
        def resp = getCategories()

        then:
        resp.status == 200
        resp.data == ["business", "entertainment", "general", "health", "science", "sports", "technology"]
    }

}
