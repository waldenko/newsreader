package com.dwalczak.newsreader.rs.model;

import java.util.Objects;
import com.dwalczak.newsreader.rs.model.Article;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ArticleList
 */
@Validated

public class ArticleList   {
  @JsonProperty("country")
  private String country = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("articles")
  @Valid
  private List<Article> articles = null;

  @JsonProperty("totaCount")
  private Integer totaCount = null;

  public ArticleList country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public ArticleList category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public ArticleList articles(List<Article> articles) {
    this.articles = articles;
    return this;
  }

  public ArticleList addArticlesItem(Article articlesItem) {
    if (this.articles == null) {
      this.articles = new ArrayList<>();
    }
    this.articles.add(articlesItem);
    return this;
  }

  /**
   * Get articles
   * @return articles
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  public ArticleList totaCount(Integer totaCount) {
    this.totaCount = totaCount;
    return this;
  }

  /**
   * Get totaCount
   * @return totaCount
  **/
  @ApiModelProperty(value = "")


  public Integer getTotaCount() {
    return totaCount;
  }

  public void setTotaCount(Integer totaCount) {
    this.totaCount = totaCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArticleList articleList = (ArticleList) o;
    return Objects.equals(this.country, articleList.country) &&
        Objects.equals(this.category, articleList.category) &&
        Objects.equals(this.articles, articleList.articles) &&
        Objects.equals(this.totaCount, articleList.totaCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(country, category, articles, totaCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArticleList {\n");
    
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    articles: ").append(toIndentedString(articles)).append("\n");
    sb.append("    totaCount: ").append(toIndentedString(totaCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

