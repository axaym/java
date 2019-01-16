import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class BookService {

  constructor(private http: Http) { }

  async searchBooks(book): Promise<any> {
    return await this.http.post('http://localhost:8081/library/book/search', book)
      .toPromise().then(res => res.json());
  }

  async searchBooksByTitle(book): Promise<any> {
    return await this.http.post('http://localhost:8081/library/book/search/title', book)
      .toPromise().then(res => res.json());
  }

  async getBookById(bookId): Promise<any> {
    return await this.http.get('http://localhost:8081/library/book/'+bookId)
      .toPromise().then(res => res.json());
  }

  async getBookCount(): Promise<any> {
    return await this.http.get('http://localhost:8081/library/book/count')
      .toPromise().then(res => res);
  }

  async getAllBooks(): Promise<any> {
    return await this.http.get('http://localhost:8081/library/book/books')
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

  async updateBook(book): Promise<any> {
    return await this.http.put('http://localhost:8081/library/book/update', book)
      .toPromise().then(res => res);
  }

}
