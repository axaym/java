import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class ParentTaskService {

  constructor(private http: Http) { }

  async getParentTasks(): Promise<any> {
    return await this.http.get('http://localhost:8081/task-manager/parenttask/tasks')
      .toPromise().then(res => res.json());
  }
}
