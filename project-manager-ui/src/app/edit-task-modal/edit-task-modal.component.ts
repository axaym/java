import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { FormBuilder, Validators } from '@angular/forms';
import { TaskObject } from '../task-object';

import { TaskService } from '../task.service';
import { ProjectManagerComponent } from '../project-manager/project-manager.component';

import * as moment from 'moment';
import { ParentTaskModalComponent } from '../parent-task-modal/parent-task-modal.component';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-edit-task-modal',
  templateUrl: './edit-task-modal.component.html',
  styleUrls: ['./edit-task-modal.component.css']
})
export class EditTaskModalComponent implements OnInit {

  @Input() selectedParentTask: any;
  @Input() selectedUser: any;
  selectedUserId: any;
  selectedParentTaskId: any;
  @Input() selectedFormData;
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

  
  constructor(public activeModal: NgbActiveModal,
    private fb: FormBuilder, 
    private taskService: TaskService,
    private modal:NgbModal) { }

  ngOnInit() {
  }

  get f() {
    return this.taskForm.controls; 
 }

 addOrUpdateItem(event) {
  this.submitted = true;    
  if (this.taskForm.invalid) {
      return;
  }
  this.addTask();
 //this.clearForm();
}

addTask() {
  let p: TaskObject = new TaskObject();
  let startDate = this.taskForm.value.startDate;
  let endDate = this.taskForm.value.endDate;
  let startDateFormatted = moment(startDate.year+'-'+startDate.month+'-'+startDate.day).format('YYYY-MM-DD');
  let endDateFormatted = moment(endDate.year+'-'+endDate.month+'-'+endDate.day).format('YYYY-MM-DD');

  if (this.selectedFormData != undefined 
      && this.selectedFormData.hasOwnProperty('taskId')
         && this.selectedFormData.taskId != undefined) {
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
   
    this.taskService.updateTask(p).
    then(
      res => this.showAlert(res._body)
    );  

  }
    
}

clearForm() {
  this.taskForm.reset();
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

}
