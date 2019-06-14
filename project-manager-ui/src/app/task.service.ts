import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class TaskService {

  constructor(private http: Http) { }

  async getTasks(): Promise<any> {
    return await this.http.get('http://localhost:8081/project-manager/task/tasks')
      .toPromise().then(res => res.json());
  }

  async addTask(task): Promise<any> {
    return await this.http.post('http://localhost:8081/project-manager/task/add',task)
      .toPromise().then(res => res);
  }

  async deleteTask(task): Promise<any> {
    return await this.http.post('http://localhost:8081/project-manager/task/delete',task)
      .toPromise().then(res => res);
  }
  async updateTask(task): Promise<any> {
    return await this.http.put('http://localhost:8081/project-manager/task/update',task)
      .toPromise().then(res => res);
  }
}
