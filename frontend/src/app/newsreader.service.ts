import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ArticleList} from "./dto/ArticleList";

@Injectable()
export class NewsreaderService {

  constructor(private http: HttpClient) { }

  getNews(category: string, pageNumber: number) {
    return this.http.get<ArticleList>('news/pl/' + category + '?pageNumber=' + pageNumber);
  }

  getCategories() {
    return this.http.get<Array<string>>('categories');
  }
}
