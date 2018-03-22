package com.dwalczak.newsreader.service.mapper

import com.dwalczak.newsreader.model.Article
import com.dwalczak.newsreader.model.ArticleList
import com.dwalczak.newsreader.newsapi.dto.NewsApiArticle
import com.dwalczak.newsreader.newsapi.dto.NewsApiArticlesResult
import com.dwalczak.newsreader.newsapi.dto.NewsApiSource
import spock.lang.Specification
import spock.lang.Unroll

import java.text.SimpleDateFormat

class ArticleMapperSpec extends Specification {

    static def SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    @Unroll
    def "check article list mapping: #srcArticles"() {
        given:
        def srcDto = [totalResults: srcArticles.size(), articles: srcArticles]
        def dstDto = [totalCount: dstArticles.size(), articles: dstArticles]

        when:
        def result = ArticleMapper.map(new NewsApiArticlesResult(srcDto))

        then:
        result.toString() == new ArticleList(dstDto).toString()

        where:
        srcArticles                                                              | dstArticles
        []                                                                       | []
        [srcArticle('x1', 'x2', 'x3', 'x4', 'x5', 'x6', '2018-03-22T21:21:34Z')] | [dstArticle('x1', 'x2', 'x3', 'x4', 'x5', 'x6', '2018-03-22T21:21:34Z')]
    }

    @Unroll
    def "failed article list mapping - empty #error"() {
        given:
        def srcDto = [totalResults: 1, articles: [srcArticle]]

        when:
        ArticleMapper.map(new NewsApiArticlesResult(srcDto))

        then:
        NullPointerException ex = thrown()
        ex.message == error

        where:
        srcArticle                                                              | error
        srcArticle('x1', null, 'x3', 'x4', 'x5', 'x6', '2018-03-22T21:21:34Z') | 'getUrl'
        srcArticle('x1', 'x2', null, 'x4', 'x5', 'x6', '2018-03-22T21:21:34Z') | 'getTitle'
        srcArticle('x1', 'x2', 'x3', 'x4', null, 'x6', '2018-03-22T21:21:34Z') | 'getSourceName'
        srcArticle('x1', 'x2', 'x3', 'x4', 'x5', 'x6', null) | 'getPublishedAt'
    }

    def static srcArticle(def author, def url, def title, def description, def sourceName, def imageUrl, def date) {
        new NewsApiArticle(
                author: author,
                url: url,
                title: title,
                description: description,
                source: new NewsApiSource(name: sourceName),
                urlToImage: imageUrl,
                publishedAt: date
        )
    }

    def static dstArticle(def author, def articleUrl, def title, def description, def sourceName, def imageUrl, def date) {
        new Article(
                author: author,
                articleUrl: articleUrl,
                title: title,
                description: description,
                sourceName: sourceName,
                imageUrl: imageUrl,
                date: SDF.parse(date))
    }
}