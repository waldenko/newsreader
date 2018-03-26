import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ArticleList} from "./dto/ArticleList";

@Injectable()
export class NewsreaderService {

  constructor(private http: HttpClient) { }

  getNews(pageNumber: number) {
    return this.http.get<ArticleList>('news/pl/technology?pageNumber=' + pageNumber);
  }
}
