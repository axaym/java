import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class SubjectService {

  constructor(private http: Http) { }
  
    async searchSubjects(subject): Promise<any> {
      return await this.http.post('http://localhost:8081/library/subject/search', subject)
        .toPromise().then(res => res.json());
    }
  
    async deleteSubject(subject): Promise<any> {
      return await this.http.post('http://localhost:8081/library/subject/delete', subject)
        .toPromise().then(res => res);
    }
  
    async addSubject(subject): Promise<any> {
      return await this.http.post('http://localhost:8081/library/subject/add', subject)
        .toPromise().then(res => res);
    }
}
