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

  constructor(private newsreaderService: NewsreaderService) {
  }

  ngOnInit() {
    this.loadNextPage();
    this.newsreaderService.getCategories()
      .subscribe(data => {
        this.categories = data
      });
  }

  loadNextPage() {
    if (this.currentPage == 0 || this.getHasMore()) {
      this.newsreaderService.getNews(this.selectedCategory,this.currentPage + 1)
        .subscribe(data => {
          this.articles = this.articles.concat(data.articles);
          this.totalCount = data.totalCount;
          ++this.currentPage;
        });
    }
  }

  setNewCategory(category: string) {
    this.selectedCategory = category;
    this.articles = []
    this.currentPage = 0;
    this.loadNextPage();
  }

  getHasMore() : boolean {
    console.log("articles: " + this.articles != null ? this.articles.length : 0);
    console.log("totalCount: " + this.totalCount != null ? this.totalCount : 0);
    return this.articles != null && this.totalCount != null
      ? this.articles.length < this.totalCount
      : false;
  }

}
