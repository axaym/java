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
  
  @Input() selectedFormData;
  @Output() itemAdded = new EventEmitter<ProjectObject>();
  @Output() itemUpdated = new EventEmitter<ProjectObject>();
  submitted = false;
  
  projectForm = this.fb.group({
    projectId: [''],
    project: ['', Validators.required],
    startDate: [''],
    endDate: [''],
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
    
    if (this.projectForm.invalid) {
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

  checkBoxSelected(event) {
    if(event.currentTarget.checked) {
        this.disableDate = false;        
    }
    else {
      this.disableDate = true;
    }
  }
}
