import { Component, OnInit } from '@angular/core';
import {Article} from "./dto/Article";
import {NewsreaderService} from "./newsreader.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  articles: Array<Article> = [];
  categories: Array<string> = []
  totalCount: Number;
  currentPage: number = 0;
  selectedCategory: string = 'technology';
  searchPhrase: String;

  constructor(private newsreaderService: NewsreaderService) {
  }

  ngOnInit() {
    this.loadNextPage();
    this.newsreaderService.getCategories()
      .subscribe(data => {
        this.categories = data
      });
  }

  resetResult() {
    this.articles = [];
    this.currentPage = 0;
    this.loadNextPage();
  }

  loadNextPage() {
    if (this.currentPage == 0 || this.getHasMore()) {
      this.newsreaderService.getNews(this.selectedCategory,this.searchPhrase, this.currentPage + 1)
        .subscribe(data => {
          this.articles = this.articles.concat(data.articles);
          this.totalCount = data.totalCount;
          ++this.currentPage;
        });
    }
  }

  setCategory(category: string) {
    this.selectedCategory = category;
    this.searchPhrase = null;
    this.resetResult();
  }


  getHasMore() : boolean {
    return this.articles != null && this.totalCount != null
      ? this.articles.length < this.totalCount
      : false;
  }

}
