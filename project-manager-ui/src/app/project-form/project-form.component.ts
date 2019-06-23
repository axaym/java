import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { FormBuilder, Validators } from '@angular/forms';
import { ProjectObject } from '../project-object';

import { ProjectService } from '../project.service';
import { ProjectManagerComponent } from 'src/app/project-manager/project-manager.component';

import * as moment from 'moment';

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.css']
})
export class ProjectFormComponent implements OnInit {
  selectedManagerId: any;
  @Input() selectedManager: any;

  @Input() submitLabel;
  @Input() disableDate:boolean;
  model: Date;
  
  
  @Input() selectedFormData;
  @Output() itemAdded = new EventEmitter<ProjectObject>();
  @Output() itemUpdated = new EventEmitter<ProjectObject>();
  submitted = false;
  @Input() dateError = false;
  
  projectForm = this.fb.group({
    projectId: [''],
    project: ['', Validators.required],
    startDate: ['', Validators.required],
    endDate: ['', Validators.required],
    status: [''],
    priority: [''],
    userId: ['']
  });

  constructor(private fb: FormBuilder, 
    private projectService: ProjectService,
  private modal:NgbModal) { }

  ngOnInit() {
    this.disableDate = true;
    this.submitLabel = "Add";
  }

  get f() {
     return this.projectForm.controls; 
  }

  addOrUpdateItem(event) {
    this.submitted = true;
    
    if (this.projectForm.invalid || this.checkDates()) {
        return;
    }
    let p: ProjectObject = new ProjectObject();
    let startDate = this.projectForm.value.startDate;
    let endDate = this.projectForm.value.endDate;
    let startDateFormatted = moment(startDate.year+'-'+startDate.month+'-'+startDate.day).format('YYYY-MM-DD');
    let endDateFormatted = moment(endDate.year+'-'+endDate.month+'-'+endDate.day).format('YYYY-MM-DD');

    if (this.selectedFormData != undefined && this.selectedFormData.hasOwnProperty('projectId') && this.selectedFormData.projectId != undefined) {
      p = this.selectedFormData;
      if (this.projectForm.controls.project.touched) {
        p.project = this.projectForm.value.project;
      }
      if (this.projectForm.controls.startDate.touched) {
        p.startDate = startDateFormatted;
      }
      if (this.projectForm.controls.endDate.touched) {
        p.endDate = endDateFormatted;
      }
      if (this.projectForm.controls.priority.touched) {
        p.priority = this.projectForm.value.priority;
      }
      if (this.projectForm.controls.userId.touched) {
        p.userId = this.selectedManagerId;
      }
      this.itemUpdated.emit(p);
    }
    else {
      p.project = this.projectForm.value.project;
      p.startDate = startDateFormatted;
      p.endDate = endDateFormatted;
      p.priority = this.projectForm.value.priority;
      p.userId = this.selectedManagerId;
      this.itemAdded.emit(p);
    }
    this.clearForm();
  }

  clearForm() {
    this.projectForm.reset();
    this.submitLabel = "Add";
    this.submitted = false;
  }

  showAlert(str) {
    alert(str);
  }

  showModal() {
    let modalRef = this.modal.open(ProjectManagerComponent);
    modalRef.componentInstance.selectedManager = this.selectedManager;
    modalRef.result.then(result => {
      console.log(result);
      this.selectedManager = result.firstName+' '+result.lastName;
      this.selectedManagerId = result.userId;
    });
  }

  callProjectFormFunc(selectedObj) {
    this.submitted = false;
    this.submitLabel = "Update";
    let sDate:Date = new Date(selectedObj.startDate);
    let eDate:Date = new Date(selectedObj.endDate);
    this.projectForm.controls.project.setValue(selectedObj.project);
    this.projectForm.controls.startDate.setValue({year: sDate.getFullYear(), month: sDate.getMonth(), day: sDate.getDate()});
    this.projectForm.controls.endDate.setValue({year: eDate.getFullYear(), month: eDate.getMonth(), day: eDate.getDate()});
    this.projectForm.controls.priority.setValue(selectedObj.priority);
    this.selectedManager = selectedObj.user.firstName+ ' ' + selectedObj.user.lastName;
    this.disableDate = false;
  }

  checkDates() {
    if(this.projectForm && this.projectForm.controls) {
      let sDate:Date = new Date(this.projectForm.value.startDate.year, this.projectForm.value.startDate.month+1, this.projectForm.value.startDate.day+1);
      let eDate:Date = new Date(this.projectForm.value.endDate.year, this.projectForm.value.endDate.month+1, this.projectForm.value.endDate.day+1);
      if(eDate.getTime() < sDate.getTime()) {
        this.dateError = true;  
        return true;
      }
      else {
        this.dateError = false;
        return false;
      }
    }
    else {
      this.dateError = false;
      return false;
    }
  }

  checkBoxSelected(event) {
    if(event.currentTarget.checked) {
        this.disableDate = false;     
        let now:Date = new Date();
        let nextDay:Date = new Date();
        nextDay.setDate(now.getDate() + 1);
        this.projectForm.controls.startDate.setValue({year: now.getFullYear(), month: now.getMonth(), day: now.getDate()});
        this.projectForm.controls.endDate.setValue({year: nextDay.getFullYear(), month: nextDay.getMonth(), day: nextDay.getDate()});
    }
    else {
      this.disableDate = true;
      this.projectForm.controls.startDate.setValue(undefined);
      this.projectForm.controls.endDate.setValue(undefined);      
    }
  }
}
