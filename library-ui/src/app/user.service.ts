import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class UserService {
  constructor(private http: Http) { }
  
    async register(user): Promise<any> {
      return await this.http.post('http://localhost:8081/library/user/registration', user)
        .toPromise().then(res => res);
    }
}
