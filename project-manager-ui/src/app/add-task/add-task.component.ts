import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { FormBuilder, Validators } from '@angular/forms';
import { TaskObject } from '../task-object';

import { TaskService } from '../task.service';
import { ProjectManagerComponent } from '../project-manager/project-manager.component';

import * as moment from 'moment';
import { ParentTaskModalComponent } from '../parent-task-modal/parent-task-modal.component';
import { ProjectModalComponent } from '../project-modal/project-modal.component';
import { ParentTaskObject } from '../parent-task-object';
import { ParentTaskService } from '../parent-task.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {
  @Input() selectedParentTask: any;
  @Input() selectedUser: any;
  @Input() selectedProject: any;

  selectedUserId: any;
  selectedParentTaskId: any;
  selectedProjectId: any;

  @Input() submitLabel;
  @Input() disableComponent:boolean = false;
  
  @Input() selectedFormData;
  @Output() itemAdded = new EventEmitter<TaskObject>();
  @Output() itemUpdated = new EventEmitter<TaskObject>();
  submitted = false;
  
  taskForm = this.fb.group({
    taskId: [''],
    task: ['', Validators.required],
    startDate: [''],
    endDate: [''],
    status: [''],
    priority: [''],
    userId: [''],
    parentId: [''],
    projectId: ['']
  });

  constructor(private fb: FormBuilder, 
    private taskService: TaskService,
    private parentTaskService : ParentTaskService,
  private modal:NgbModal) { }

  ngOnInit() {
    this.disableComponent = false;
    this.submitLabel = "Add";
  }

  get f() {
     return this.taskForm.controls; 
  }

  addOrUpdateItem(event) {
    this.submitted = true;    
    if (this.taskForm.invalid) {
        return;
    }
    if(this.disableComponent) {
        this.addParentTask();
    }
    else {
        this.addTask();
    }    
    this.clearForm();
  }

  addTask() {
    let p: TaskObject = new TaskObject();
    let startDate = this.taskForm.value.startDate;
    let endDate = this.taskForm.value.endDate;
    let startDateFormatted = moment(startDate.year+'-'+startDate.month+'-'+startDate.day).format('YYYY-MM-DD');
    let endDateFormatted = moment(endDate.year+'-'+endDate.month+'-'+endDate.day).format('YYYY-MM-DD');

    if (this.selectedFormData != undefined && this.selectedFormData.hasOwnProperty('taskId') && this.selectedFormData.taskId != undefined) {
      p = this.selectedFormData;
      if (this.taskForm.controls.task.touched) {
        p.task = this.taskForm.value.task;
      }
      if (this.taskForm.controls.startDate.touched) {
        p.startDate = startDateFormatted;
      }
      if (this.taskForm.controls.endDate.touched) {
        p.endDate = endDateFormatted;
      }
      if (this.taskForm.controls.priority.touched) {
        p.priority = this.taskForm.value.priority;
      }
      if (this.taskForm.controls.userId.touched) {
        p.userId = this.selectedUserId;
      }
      if (this.taskForm.controls.parentId.touched) {
        p.parentId = this.selectedParentTaskId;
      }
      if (this.taskForm.controls.projectId.touched) {
        p.projectId = this.selectedProjectId;
      }
      this.taskService.updateTask(p).
      then(
        res => this.showAlert(res._body)
      );  
 
    }
    else {
      p.task = this.taskForm.value.task;
      p.startDate = startDateFormatted;
      p.endDate = endDateFormatted;
      p.priority = this.taskForm.value.priority;
      p.userId = this.selectedUserId;
      p.parentId = this.selectedParentTaskId;
      p.projectId = this.selectedProjectId;
      this.taskService.addTask(p).
      then(
        res => this.showAlert(res._body)
      );  
    }
  }

  addParentTask() {
    let p: ParentTaskObject = new ParentTaskObject();    
    p.parentTask = this.taskForm.value.task;
    this.parentTaskService.addParentTask(event).
    then(
      res => this.showAlert(res._body)
    );       
  }

  clearForm() {
    this.taskForm.reset();
    this.submitLabel = "Add";
    this.submitted = false;
  }

  showAlert(str) {
    alert(str);
  }

  showUserModal() {
    let modalRef = this.modal.open(ProjectManagerComponent);
    modalRef.componentInstance.selectedManager = this.selectedUser;
    modalRef.result.then(result => {
      console.log(result);
      this.selectedUser = result.firstName+' '+result.lastName;
      this.selectedUserId = result.userId;
    });
  }

  showParentTaskModal() {
    let modalRef = this.modal.open(ParentTaskModalComponent);
    modalRef.componentInstance.selectedParentTask = this.selectedParentTask;
    modalRef.result.then(result => {
      console.log(result);
      this.selectedParentTask = result.parentTask;
      this.selectedParentTaskId = result.parentId;
    });
  }

  showProjectModal() {
    let modalRef = this.modal.open(ProjectModalComponent);
    modalRef.componentInstance.selectedProject = this.selectedProject;
    modalRef.result.then(result => {
      console.log(result);
      this.selectedProject = result.project;
      this.selectedProjectId = result.projectId;
    });
  }

  checkBoxSelected(event) {
    if(event.currentTarget.checked) {
        this.disableComponent = true;        
    }
    else {
      this.disableComponent = false;
    }
  }
}

