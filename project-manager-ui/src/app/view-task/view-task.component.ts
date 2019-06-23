import { Component, OnInit, Input, Inject } from '@angular/core';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { TaskService } from '../task.service';
import { TaskObject } from '../task-object';

import { AddTaskComponent } from '../add-task/add-task.component';
import * as moment from 'moment';
import { ProjectModalComponent } from '../project-modal/project-modal.component';
import { EditTaskModalComponent } from 'src/app/edit-task-modal/edit-task-modal.component';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {

  @Input() taskListData;
  @Input() selectedProject: any;
  selectedProjectId: any;

  constructor(private taskService: TaskService, 
      private modal:NgbModal) { }

  ngOnInit() {
    
  }

  getTasksByProject() {
    let task : TaskObject = new TaskObject();
    task.projectId = this.selectedProjectId;
    this.taskService.getTasksByProject(task).
    then(
      res => this.taskListData = res
    );
  }

  endTask(event) {
    let task:TaskObject = new TaskObject();
    Object.assign(task, event);
    task.status = 1;
    this.taskService.updateTask(task).
    then(
      res => this.showAlertAndRefresh(res._body, "add")
    );
  }

  

  deleteTask(event) {
    this.taskService.deleteTask(event).
    then(
      res => this.showAlertAndRefresh(res._body, "delete")
    );
  }

  showAlertAndRefresh(status, type) {
    alert("task "+type+" status: " + status);
    this.getTasksByProject();
  }

  showProjectModal() {
    let modalRef = this.modal.open(ProjectModalComponent);
    modalRef.componentInstance.selectedProject = this.selectedProject;
    modalRef.result.then(result => {
      this.selectedProject = result.project;
      this.selectedProjectId = result.projectId;
      this.getTasksByProject();
    });
  }

  editTask(event) {
    let modalRef = this.modal.open(EditTaskModalComponent, {size: 'lg'});
    modalRef.componentInstance.selectedFormData = event;
    modalRef.componentInstance.callTaskFormFunc(event);
    
    modalRef.result.then(result => {
      this.getTasksByProject();
    });
  }

  sortList(prop) {
    this.taskListData = this.taskListData.sort(function(a, b){
      var x = a[prop].toLowerCase();
      var y = b[prop].toLowerCase();
      if (x < y) {return -1;}
      if (x > y) {return 1;}
      return 0;
    });
  }

  sortDate(prop) {
    this.taskListData = this.taskListData.sort(function(a, b){      
      let dateA = new Date(a[prop]);
      let dateB = new Date(b[prop]);      
     return dateB.getTime() - dateA.getTime();
    });
  }

  sortNumber(prop) {
    this.taskListData = this.taskListData.sort(function(a, b){      
      var x = a[prop];
      var y = b[prop];
      if (x < y) {return -1;}
      if (x > y) {return 1;}
      return 0;
    });
  }
}
