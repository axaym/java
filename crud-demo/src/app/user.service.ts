import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class UserService {

  constructor(private http: Http) { }

  async getUsers(): Promise<any> {
    return await this.http.get('https://jsonplaceholder.typicode.com/posts')
      .toPromise().then(res => res.json());
  }

  async getUser(): Promise<any> {
    return await this.http.get('https://jsonplaceholder.typicode.com/posts/1')
      .toPromise().then(res => res.json());
  }

  async postUser(): Promise<any> {
    return await this.http.post('https://jsonplaceholder.typicode.com/posts', {
      title: 'foo',
      body: 'bar',
      userId: 1
    })
      .toPromise().then(res => res.json());
  }

  async updateUser(): Promise<any> {
    return await this.http.put('https://jsonplaceholder.typicode.com/posts/1', {
      title: 'foo1',
      body: 'bar1',
      userId: 1
    })
      .toPromise().then(res => res.json());
  }

  async deleteUser(): Promise<any> {
    return await this.http.delete('https://jsonplaceholder.typicode.com/posts/1')
      .toPromise().then(res => res.json());
  }
}
