import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class UserService {

  constructor(private http: Http) { }

  async getUsers(): Promise<any> {
    return await this.http.get('http://localhost:8081/project-manager/user/users')
      .toPromise().then(res => res.json());
  }

  async addUser(user): Promise<any> {
    return await this.http.post('http://localhost:8081/project-manager/user/add',user)
      .toPromise().then(res => res);
  }

  async deleteUser(user): Promise<any> {
    return await this.http.post('http://localhost:8081/project-manager/user/delete',user)
      .toPromise().then(res => res);
  }
  async updateUser(user): Promise<any> {
    return await this.http.put('http://localhost:8081/project-manager/user/update',user)
      .toPromise().then(res => res);
  }
}

