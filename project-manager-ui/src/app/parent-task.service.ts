import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class ParentTaskService {

  constructor(private http: Http) { }

  async getParentTasks(): Promise<any> {
    return await this.http.get('http://localhost:8081/project-manager/parenttask/tasks')
      .toPromise().then(res => res.json());
  }

  async addParentTask(parentTask): Promise<any> {
    return await this.http.post('http://localhost:8081/project-manager/parenttask/add',parentTask)
      .toPromise().then(res => res);
  }

}
