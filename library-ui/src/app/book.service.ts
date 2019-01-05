import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class BookService {

  constructor(private http: Http) { }

  async searchBooks(book): Promise<any> {
    return await this.http.post('http://localhost:8081/library/book/search', book)
      .toPromise().then(res => res.json());
  }

  async deleteBook(book): Promise<any> {
    return await this.http.post('http://localhost:8081/library/book/delete', book)
      .toPromise().then(res => res);
  }

  async addBook(book): Promise<any> {
    return await this.http.post('http://localhost:8081/library/book/add', book)
      .toPromise().then(res => res);
  }

}
