package com.dwalczak.newsreader.rs.mapper

import spock.lang.Specification
import spock.lang.Unroll

import java.text.SimpleDateFormat
import java.time.LocalDate

class ArticleMapperSpec extends Specification {

    static def SDF = new SimpleDateFormat('yyyy-MM-dd')

    @Unroll
    def "check article list mapping: #srcArticles"() {
        given:
        def srcDto = [totalCount: srcArticles.size(), articles: srcArticles]
        def dstDto = [country: 'x1', category: 'x2', totalCount: dstArticles.size(), articles: dstArticles]

        when:
        def result = ArticleMapper.map(dstDto.country, dstDto.category, new com.dwalczak.newsreader.model.ArticleList(srcDto))

        then:
        result.toString() == new com.dwalczak.newsreader.rs.model.ArticleList(dstDto).toString()

        where:
        srcArticles                                                    | dstArticles
        []                                                             | []
        [srcArticle('x1', 'x2', 'x3', 'x4', 'x5', 'x6', '2018-03-22')] | [dstArticle('x1', 'x2', 'x3', 'x4', 'x5', 'x6', 2018, 3, 22)]
    }

    def static srcArticle(def author, def articleUrl, def title, def description, def sourceName, def imageUrl, def date) {
        new com.dwalczak.newsreader.model.Article(
                author: author,
                articleUrl: articleUrl,
                title: title,
                description: description,
                sourceName: sourceName,
                imageUrl: imageUrl,
                date: SDF.parse(date))
    }
    def static dstArticle(def author, def articleUrl, def title, def description, def sourceName, def imageUrl, int dateYear, int dateMonth, int dateDayOfMonth) {
        new com.dwalczak.newsreader.rs.model.Article(
                author: author,
                articleUrl: articleUrl,
                title: title,
                description: description,
                sourceName: sourceName,
                imageUrl: imageUrl,
                date: LocalDate.of(dateYear, dateMonth, dateDayOfMonth))
    }

}
