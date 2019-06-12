import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class ProjectService {

  constructor(private http: Http) { }

  async getProjects(): Promise<any> {
    return await this.http.get('http://localhost:8081/project-manager/project/projects')
      .toPromise().then(res => res.json());
  }

  async addProject(project): Promise<any> {
    return await this.http.post('http://localhost:8081/project-manager/project/add',project)
      .toPromise().then(res => res);
  }

  async deleteProject(project): Promise<any> {
    return await this.http.post('http://localhost:8081/project-manager/project/delete',project)
      .toPromise().then(res => res);
  }
  async updateProject(project): Promise<any> {
    return await this.http.put('http://localhost:8081/project-manager/project/update',project)
      .toPromise().then(res => res);
  }
}

